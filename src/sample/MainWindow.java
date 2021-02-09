package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class MainWindow {
    public GridPane pane;
    public HBox hBox;

    EventHandler<MouseEvent> mouseExitedListener = actionEvent -> {
        EventTarget target = actionEvent.getTarget();
        if (target instanceof Button) { // check if clicked element was a button
            Button button = (Button) target; // set  the object to a button
            button.setText("");

        }
    };
    private String turn = "x";
    EventHandler<ActionEvent> actionEventHandler = actionEvent -> {
        EventTarget target = actionEvent.getTarget();
        if (target instanceof Button) { // check if clicked element was a button
            Button button = (Button) target; // set  the object to a button
            button.setOnAction(null);
            button.setOnMouseEntered(null);
            button.setOnMouseExited(null);
            button.setStyle("-fx-font-size:45; -fx-text-fill: black");
            button.setText(turn);
            CheckRowForSameItems((int) button.getTranslateX(), (int) button.getTranslateY(), Direction.DOWN);
            changeTurn();


        }
    };
    EventHandler<MouseEvent> mouseEnteredListener = actionEvent -> {
        EventTarget target = actionEvent.getTarget();
        if (target instanceof Button) { // check if clicked element was a button
            Button button = (Button) target; // set  the object to a button
            button.setText(turn);
        }

    };

    public boolean CheckRowForSameItems(final int x, final int y, Direction direction) {
        if (x == 0 && y == 0) {
            System.out.println(pane.getChildren().get(2).getLayoutX());
        }
        return true;
    }

    public void changeTurn() {
        if (turn.equals("x")) {
            turn = "o";
        } else if (turn.equals("o")) {
            turn = "x";
        }
    }

    public void initElements() {
        createButtons();
    }


    public void createButtons() {


        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                Button button = new Button();

                button.setPrefHeight(100);
                button.setPrefWidth(100);
                button.setMaxHeight(Double.MAX_VALUE);
                button.setMaxWidth(Double.MAX_VALUE);
                button.setOnAction(actionEventHandler);

                button.setStyle("-fx-font-size:45; -fx-text-fill: gray");
                button.setOpacity(30);


                button.setOnMouseEntered(mouseEnteredListener);
                button.setOnMouseExited(mouseExitedListener);

                pane.add(button, x, y);


            }
        }
    }

}