package server;

import java.util.ArrayList;
import java.util.List;

//данный клаз создан для реализации интерфейса AuthService
public class SimpleAuthService implements AuthService {



    private class UserData{
        //создаем поля
        String login;
        String password;
        String nickname;
        //конструктор
        public UserData(String login, String password, String nickname) {
            this.login = login;
            this.password = password;
            this.nickname = nickname;
        }
    }
    //лист клиентов
    List<UserData> users;
    //метод отвечает за добавление нового клиента
    public SimpleAuthService() {
        users = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            users.add(new UserData("login"+i,"pass"+i,"nick"+i));
        }
        users.add(new UserData("1111@mail.ru","1111","Лелик"));
        users.add(new UserData("2222@mail.ru","2222","Болик"));
    }
    //метод осуществляет проверку всех клиентов в листе на совпадение введенного логина и пароля,
    //в случае корректного ввода возвращает nickname
    @Override
    public String getNickByLoginAndPassword(String login, String password) {
        for (UserData user : users) {
                if(user.login.equals(login) && user.password.equals(password)){
                    return user.nickname;
                }
        }
        return null;



    }
    //метод регистрации , сравнивает ,нет ли совпадений среди зарегистрированных пользователей
    //если нет добавляет нового к списку
    @Override
    public boolean registration(String login, String password, String nickname) {

        for (UserData user : users) {
            if (user.login.equals(login) || user.nickname.equals(nickname)){
                return false;
            }

        }
        users.add(new UserData(login, password, nickname));
        return true;



    }
}
