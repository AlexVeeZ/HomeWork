package Server;
//данный интерфейс создан для хранения учетных данных клиентов
public interface AuthService {


    /**
     *  данный метод возвращает null ,если нет совпадений по логину и паролю
     *   возвращает nickname ,если есть совпадение.
     **/
    String getNickByLoginAndPassword(String login, String password);











}
