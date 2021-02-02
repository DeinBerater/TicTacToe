package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class MainWindow {
    public GridPane pane;
    public HBox hBox;
    EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            EventTarget target = actionEvent.getTarget();
            if (target instanceof Button) { // check if clicked element was a button
                Button button = (Button) target; // set  the object to a button
                button.setText("x");
            }
        }
    };


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
                button.setOnAction(buttonHandler);
                button.setStyle("-fx-font-size:45");
                pane.add(button, x, y);


            }
        }
    }

}