package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

//класс, отвечающий за работу с различными клиентами
public class ClientHandler {

    //ссылка на наш сервер
    private Server server;
    //сокет, передаваемый сервером для каждого подключившегося клиента
    private Socket socket;

    //объявляем экземпляры классов для ввода/ вывода инфо (сообщений)
    private DataInputStream in;
    private DataOutputStream out;

    private String nickname;
    private String login;

    //создаем конструктор ,передаем на вход параметры сервера/сокета
    public ClientHandler(Server server, Socket socket){
        try {
            this.server = server;
            this.socket = socket;

            //объект класса, позволяющий считывать инфо и назначаем на входящий поток сокета
            in = new DataInputStream(socket.getInputStream());
            //объект класса, позволяющий отправлять инфо и назначаем на исходящий поток сокета
            out = new DataOutputStream(socket.getOutputStream());

            //используем доп. поток чтобы не заблочился наш графич. интерфейс
            //т.е ,"бесконечный" цикл while работает в безопасном потоке
            new Thread(()-> {
                    try {
                        //запуск цикла авторизации
                        while (true) {
                            //инициализируем строковую переменную, которая считывает инфо
                            String str = in.readUTF();

                            if (str.startsWith("/auth ")) {
                                String[] token = str.split("\\s");
                                if(token.length < 3){
                                    continue;
                                }
                                String newNick = server
                                        .getAuthService()
                                        .getNickByLoginAndPassword(token[1], token[2]);
                                login = token[1];
                                if (newNick != null){
                                    if (!server.isLoginAuthenticated(login)){
                                        nickname = newNick;
                                        sendMsg("/authok "+nickname);
                                        server.subscribe(this);
                                        System.out.println("Клиент "+nickname+" подключился");

                                        sendMsg(SQLHandler.getMessageForNick(nickname));

                                        break;
                                    }else {
                                        sendMsg("Аутентификация уже пройдена, " +
                                                "для повторной аутентификации смените учетную запись");

                                    }


                                }else {
                                    sendMsg("Неверный логин / пароль");

                                }
                            }

                            if (str.startsWith("/reg ")) {
                                String[] token = str.split("\\s");
                                if (token.length < 4) {
                                    continue;
                                }
                                boolean b = server.getAuthService()
                                        .registration(token[1], token[2], token[3]);
                                if (b) {
                                    sendMsg("/regok");
                                } else {
                                    sendMsg("/regno");
                                }
                            }

                        }



                        //запуск постоянного цикла для переписки
                        while (true) {
                            //инициализируем строковую переменную, которая считывает инфо
                            String str = in.readUTF();
                            //ветвление на случай отключения от чата
                            if (str.startsWith("/")){
                                System.out.println(str);
                                if (str.equals("/end")) {
                                    //информация об отключении дублируется на сервер
                                    out.writeUTF("/end");
                                    break;
                                }//далее идет код ,ответственный за приватные сообщения
                                if (str.startsWith("/w")) {
                                    String[] token = str.split("\\s+", 3);
                                    if (token.length < 3) {
                                        continue;
                                    }
                                    server.privateMsg(this, token[1], token[2]);

                                    if (str.startsWith("/chnick")){
                                        String[] tken = str.split(" ",2);
                                        if(tken.length<2){
                                            continue;
                                        }
                                        if ((tken[1].contains(" "))) {
                                            sendMsg("Ник не может содержать пробелов");
                                            continue;
                                        }
                                        if (server.getAuthService().changeNick(this.nickname, tken[1])){
                                            sendMsg("/yournickis " + tken[1]);
                                            sendMsg("Твой новый ник: " + tken[1]);
                                            this.nickname = tken[1];
                                            server.broadcastClientList();
                                        }else{
                                            sendMsg("Ник "+tken[1] + " уже существует");
                                        }
                                    }




                                }

                            }else{
                                server.broadcastMsg(this, str);
                            }
                        }
                        //отлавливаем возможные события
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        //попав в блок в finally ,мы уверены ,что клиент отключился
                        //и оповещаем об этом
                        System.out.println("Клиент отключился");
                        server.unsubscribe(this);
                        try {
                            //закрываем сокет после работы
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

            }).start();
        }catch (IOException e) {
            e.printStackTrace();
        }


    }

    //создаем отдельный метод по отправке сообщений
    void sendMsg(String msg){
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNickname() {
        return nickname;
    }

    public String getLogin() {
        return login;
    }
}
