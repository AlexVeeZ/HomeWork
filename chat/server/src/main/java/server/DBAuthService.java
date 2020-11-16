package server;

public class DBAuthService implements AuthService {

    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        return SQLHandler.getNicknameByLoginAndPassword(login, password);
    }


    @Override
    public String getNickByLoginAndPassword(String login, String password) {
        return null;
    }

    @Override
    public boolean registration(String login, String password, String nickname) {
        return SQLHandler.registration(login, password, nickname);
    }


    @Override
    public boolean changeNick(String previousNick, String newNick) {
        return SQLHandler.changeNick(previousNick, newNick);
    }
}


