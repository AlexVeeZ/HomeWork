package client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    //объявляем поля, переопределенные из fxml
    @FXML
    public TextArea textArea;
    @FXML
    public TextField textField;
    //поле панели авторизации
    @FXML
    public HBox authPanel;
    //поле для ввода логина
    @FXML
    public TextField loginField;
    //поле для ввода пароля
    @FXML
    public PasswordField passwordField;
    //поле панели переписки
    @FXML
    public HBox msgPanel;
    //поле панели списка контактов
    @FXML
    public ListView<String> clientList;

    //объявляем поля (реквизиты) для подключения к серверу
    private final String IP_ADDRESS = "localhost";
    private final int PORT = 8189;


    //объявляем поле клиентского сокета
    private Socket socket;
    //объявляем экземпляры классов для ввода/ вывода инфо (сообщений)
    DataInputStream in;
    DataOutputStream out;

    //булевое значение авторизировался/не авторизировался клиент
    private boolean authenticated;
    //переменная никнейм
    private String nickname;
    private final  String TITLE = "#OrangeChat";
    //ссылка на окно чата
    private Stage stage;
    //ссылка на окно регистрации
    private Stage regStage;
    //ссылка на RegController
    private RegController regController;


    //данный метод отвечает за отбражение панелей авторизации (authPanel) и переписки (msgPanel)
    //когда клиент успешно прошел авторизацию ,отобразится панель переписки
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
        //панель аунтефикации становится невидимой
        authPanel.setVisible(!authenticated);
        authPanel.setManaged(!authenticated);
        //панель отправки сообщений становится видимой
        msgPanel.setVisible(authenticated);
        msgPanel.setManaged(authenticated);
        //панель списка контактов становится видимой
        clientList.setVisible(authenticated);
        clientList.setManaged(authenticated);
        //если авторизация не пройдена nickname = null
        if(!authenticated){
            nickname = "";

        }
        setTitle(nickname);

    }



    //т.к мы имплементим интерфейс, необходима реализация данного метода
    //он отвечает за работу кода ,после выполнения всех операций другого класса,
    //в нашем случае класса Server
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //после запуска чата статус не пройденной авторизации (по умолчанию)
        setAuthenticated(false);
        //создаем регистр форму
        createRegWindow();
        //участок кода , отвечающий за выход, при нажатии красного крестика в окне приложения
        Platform.runLater(()->{
            stage = (Stage) textField.getScene().getWindow();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    System.out.println("Bye");
                    //если после закрытие окна сокет все еще открыт
                    //принудительно отправляем сообщение на сервер о закрытии сокета
                    if(socket != null && !socket.isClosed()){
                        try {
                            out.writeUTF("/end");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });

        });

    }
    //данный метод реализует попытку подключения к серверу и работу чата
    private void connect(){
        try {
            //указываем данные серверного сокета для соединения
            socket = new Socket(IP_ADDRESS, PORT);
            //объявляем объект класса, позволяющий считывать инфо и назначаем на входящий поток сокета
            in = new DataInputStream(socket.getInputStream());
            //объявляем объект класса, позволяющий отправлять инфо и назначаем на исходящий поток сокета
            out = new DataOutputStream(socket.getOutputStream());
            //используем доп. поток чтобы не заблочился наш графич. интерфейс
            //т.е ,"бесконечный" цикл while работает в безопасном потоке
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //запуск цикла авторизации
                        while (true) {
                            //инициализируем строковую переменную, которая считывает инфо
                            String str = in.readUTF();

                            if(str.equals("/end")){
                                throw new RuntimeException("Сервер закрыл соединение за долгое бездействие");

                            }
                            //ветвление при успешной авторизации, сервер получает
                            //сообщение "/authok", после чего выподаем из цикла и входим в цикл переписки
                            if (str.startsWith("/authok")) {
                                nickname = str.split(" ",2)[1];
                                setAuthenticated(true);
                                break;
                            }

                            if (str.startsWith("/regok")) {
                                regController.addMsgToTextArea("Новый клиент зарегистрирован");
                            }
                            if (str.startsWith("/regno")) {
                                regController.addMsgToTextArea("Что-то пошло не так...");
                            }

                            //вывод сообщения в textArea
                            textArea.appendText(str+"\n");
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            textArea.clear();

                        }
                        //запуск постоянного цикла для переписки
                        while (true) {
                            //инициализируем строковую переменную, которая считывает инфо
                            String str = in.readUTF();
                            //ветвление для актуального списка клиентов онлайн
                            //сообщение , начинается с "/" - своеобразная команда для сервера
                            if(str.startsWith("/")){
                                //ветвление на случай отключения от чата
                                if (str.equals("/end")) {
                                    break;
                                }
                                if(str.startsWith("/clientlist ")){
                                    //представили строку ввиде массива и разделили элементы по пробелу (\\s)
                                    String[] token = str.split("\\s+");
                                    //очищаем клиент лист и через счетчик обновляем (добавляем ники) онлайн
                                    Platform.runLater(()->{
                                        clientList.getItems().clear();
                                        for (int i = 1; i < token.length; i++) {
                                            clientList.getItems().add(token[i]);
                                        }



                                    });

                                }
                            }else{
                                //вывод сообщения в textArea
                                textArea.appendText(str+"\n");
                            }



                        }
                        //отлавливаем возможные события
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        //попав в блок в finally ,мы уверены ,что клиент отключился
                        //и оповещаем об этом
                        System.out.println("Вы покинули чат");
                        setAuthenticated(false);
                        try {
                            //закрываем сокет после работы
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //метод для отправки сообщения ,нажатием на кнопку "Send"
    public void sendMsg(ActionEvent actionEvent) {

        try {
            //отправка сообщения, взятого из текстовой строки (textField)
            out.writeUTF(textField.getText());
            //после отправки очищаем строку
            textField.clear();
            //возвращаем курсив (фокус) на строку
            textField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    //метод срабатывает при вводе логина и пароля
    public void tryToAuth(ActionEvent actionEvent) {
        //если сокет равен null или он закрыт, значит пользователю он не присвоен
        //в рамках метода connect() присваивается новый сокет
        if(socket == null || socket.isClosed()){
            connect();
        }
        //на сервер отправляется текст с введенным логином и паролем от клиента
        //*метод trim() убирает, случайным образом введенные пробелы + логин приводится к нижнему регистру
        try {
            out.writeUTF((String.format("/auth %s %s",loginField.getText().trim().toLowerCase(),
                    passwordField.getText().trim())));
                passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setTitle(String nick){
        Platform.runLater(()->{
            ((Stage)textField.getScene().getWindow()).setTitle(TITLE +" "+ nick);





        });


    }
    //метод позволяет при клике мышки на нике в клиент листе,
    //сделать заготовку на приватное сообщение
    public void clickClientList(MouseEvent mouseEvent) {
        System.out.println(clientList.getSelectionModel().getSelectedItem());
        String receiver = clientList.getSelectionModel().getSelectedItem();
        textField.setText("/w "+receiver+" ");
    }

    private void createRegWindow(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/reg.fxml"));
            Parent root = fxmlLoader.load();
            regStage = new Stage();
            regStage.setTitle("#OrangeChat: Registration form");
            regStage.setScene(new Scene(root, 405, 275));

            regController = fxmlLoader.getController();
            regController.setController(this);
            //пока не закрыли форму регистрации , прочие окна недоступны
            regStage.initModality(Modality.APPLICATION_MODAL);

        }catch(IOException e){
            e.printStackTrace();
        }



    }

    //при нажатии кнопки "NEW!" показывается форма регистрации
    public void registration(ActionEvent actionEvent) {
        regStage.show();
    }

    //метод на вход принимает рег. форму и отправляет ее на сервер
    public void tryToReg(String login, String password, String nickname){
        String msg = String.format("/reg %s %s %s",login,password,nickname);
        //проверка на "закрытость" сокета
        if(socket == null || socket.isClosed()){
            connect();
        }

        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(msg);
    }

}