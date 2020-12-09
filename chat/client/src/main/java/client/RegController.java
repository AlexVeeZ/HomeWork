package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

//контроллер для окна регистрации
public class RegController {



    //поле "основного" контроллера
    private Controller controller;



    //поля для активации нового клиента
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField nickField;
    @FXML
    private TextArea textArea;

    public void tryToReg(ActionEvent actionEvent) {
        controller.tryToReg(loginField.getText().trim(),passwordField.getText().trim(),
                nickField.getText().trim());

    }
    //создали сеттер ,чтобы был доступ к приватному полю
    public void setController(Controller controller) {
        this.controller = controller;
    }
    public void addMsgToTextArea(String msg){
        textArea.appendText(msg+"\n");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        textArea.clear();

    }



}
