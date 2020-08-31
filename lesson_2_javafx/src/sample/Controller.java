package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    public TextField myTextField;
    @FXML
    private TextArea myTextArea;

    public void onClickBtnSend(ActionEvent actionEvent) {
        myTextArea.appendText("Я: "+myTextField.getText()+"\n");
        myTextField.clear();
        myTextField.requestFocus();
    }


}
