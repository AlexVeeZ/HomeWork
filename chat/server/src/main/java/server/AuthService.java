package server;
//данный интерфейс создан для хранения учетных данных клиентов
public interface AuthService {


    String getNicknameByLoginAndPassword(String login, String password);

    String getNickByLoginAndPassword(String login, String password);

    boolean registration(String login, String password, String nickname);

    boolean changeNick(String previousNick, String newNick);


}
