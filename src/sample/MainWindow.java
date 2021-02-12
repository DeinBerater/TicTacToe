package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.awt.*;
import java.util.ArrayList;

public class MainWindow {
    public GridPane pane;
    public HBox hBox;

    private String turn = "x";
    String[][] field;
    EventHandler<MouseEvent> mouseEnteredHandler = actionEvent -> {
        EventTarget target = actionEvent.getTarget();
        if (target instanceof Button) { // check if hovered element is a button
            Button button = (Button) target; // set  the object to a button
            button.setText(turn);
        }

    };
    EventHandler<MouseEvent> mouseExitedHandler = actionEvent -> {
        EventTarget target = actionEvent.getTarget();
        if (target instanceof Button) { // check if hovered element was a button
            Button button = (Button) target; // set  the object to a button
            button.setText("");
        }
    };
    EventHandler<ActionEvent> actionEventHandler = actionEvent -> {
        EventTarget target = actionEvent.getTarget();
        if (target instanceof Button) { // check if clicked element is a button
            Button buttonTarget = (Button) target; // set  the object to a button
            buttonTarget.setOnAction(null);
            buttonTarget.setOnMouseEntered(null);
            buttonTarget.setOnMouseExited(null);
            buttonTarget.setStyle("-fx-font-size:45; -fx-text-fill: #000000");
            buttonTarget.setText(turn);

            field[GridPane.getRowIndex(buttonTarget)][GridPane.getColumnIndex(buttonTarget)] = turn;


            ArrayList<Point> winCoords = CheckForWin(buttonTarget);
            if (winCoords != null) {
                // win
                for (Point point : winCoords) {

                    Node node = getNodeByRowColumnIndex(point.x, point.y, pane);

                    if (node instanceof Button) { // check if the element is a button
                        Button targetButton = (Button) node; // set the node to a button

                        targetButton.setStyle("-fx-font-size:45; -fx-text-fill: #12b012");
                    }

                }

                ObservableList<Node> children = pane.getChildren();

                for (Node node : children) { // loop every element in the pane
                    if (node instanceof Button) {
                        Button button = (Button) node;

                        button.setOnMouseEntered(null);
                        button.setOnMouseExited(null);

                        button.setOnAction(resetActionEventHandler);
                    }
                }

            }
            switchTurn();
        }
    };
    EventHandler<ActionEvent> resetActionEventHandler = actionEvent -> {
        EventTarget target = actionEvent.getTarget();
        resetButtons();
    };



    public ArrayList<Point> CheckForWin(final Button button) {


        int x = GridPane.getColumnIndex(button);
        int y = GridPane.getRowIndex(button);


        ArrayList<Point> points = new ArrayList<>();
        // check for the same items in the row
        for (int i = 0; i < field.length; i++) { // check the columns in the row for the right item
            if (field[y][i] == null || !field[y][i].equals(turn)) {
                // stop when an item does not match
                break;
            }
            points.add(new Point(i, y));
            if (i == field.length - 1) { // If it's the last item, all items must be correct because else it would leave the loop
                return points;
            }
        }

        points.clear();
        // check for the same items in the column
        for (int i = 0; i < field[0].length; i++) { // check the rows in the column for the right item
            if (field[i][x] == null || !field[i][x].equals(turn)) {
                // stop when an item does not match
                break;
            }
            points.add(new Point(x, i));
            if (i == field[0].length - 1) { // If it's the last item, all items must be correct because else it would leave the loop
                return points;
            }
        }

        if (x == y) {
            points.clear();
            // check if something was placed in the diagonal way from top left to bottom right
            for (int i = 0; i < field[0].length; i++) { // check the diagonal way for the right item
                if (field[i][i] == null || !field[i][i].equals(turn)) {
                    // stop when an item does not match
                    break;
                }
                points.add(new Point(i, i));
                if (i == field.length - 1) { // If it's the last item, all items must be correct because else it would leave the loop
                    return points;
                }
            }
        }


        points.clear();
        if (x == (field.length - 1) - y) {
            int lastSlot = field.length - 1;

            // check if something was placed in the diagonal way from bottom left to top right
            for (int i = 0; i < field[0].length; i++) { // check the diagonal way for the right item

                if (field[lastSlot - i][i] == null || !field[lastSlot - i][i].equals(turn)) {
                    // stop when an item does not match
                    break;
                }
                points.add(new Point(i, lastSlot - i));
                if (i == field.length - 1) { // If it's the last item, all items must be correct because else it would leave the loop
                    return points;
                }
            }
        }


        return null;
    }

    public Node getNodeByRowColumnIndex(final int x, final int y, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> children = gridPane.getChildren();

        for (Node node : children) {
            if (GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node) == y) {
                result = node;
                break;
            }
        }

        return result;
    }

    public void switchTurn() {
        if (turn.equals("x")) {
            turn = "o";
        } else if (turn.equals("o")) {
            turn = "x";
        }
    }

    public void initElements() {
        createButtons();
        resetButtons();
    }


    public void createButtons() {


        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                Button button = new Button();

                button.setPrefHeight(100);
                button.setPrefWidth(100);
                button.setMaxHeight(Double.MAX_VALUE);
                button.setMaxWidth(Double.MAX_VALUE);

                pane.add(button, x, y);

            }
        }
    }

    public void resetButtons() {
        ObservableList<Node> children = pane.getChildren();

        for (Node node : children) {
            if (node instanceof Button) {
                Button button = (Button) node;
                button.setText("");
                button.setStyle("-fx-font-size:45; -fx-text-fill: gray");
                button.setOpacity(30);

                button.setOnAction(actionEventHandler);

                button.setOnMouseEntered(mouseEnteredHandler);
                button.setOnMouseExited(mouseExitedHandler);

                // redeclare two dimensional array field
                field = new String[3][3];
            }
        }
    }

}