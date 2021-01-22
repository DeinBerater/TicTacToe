package sample;

import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.scene.control.Button;

public class MainWindow {


    public void setIcon(ActionEvent actionEvent) {
        EventTarget target = actionEvent.getTarget();
        if (target instanceof Button) { // check if clicked element was a button
            Button button = (Button) target; // set  the object to a button
            button.setText("x");
        }
    }


}