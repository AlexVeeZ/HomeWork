package Server;

import Client.Controller;

import java.awt.*;
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
                                String newNick = server
                                        .getAuthService()
                                        .getNickByLoginAndPassword(token[1], token[2]);
                                if (newNick != null){
                                    nickname = newNick;
                                    sendMsg("/authok "+nickname);
                                    server.subscribe(this);
                                    System.out.println("Клиент "+nickname+" подключился");
                                    break;
                                }else{
                                    sendMsg("Неверный логин / пароль");

                                }

                            }


                        }



                        //запуск постоянного цикла для переписки
                        while (true) {
                            //инициализируем строковую переменную, которая считывает инфо
                            String str = in.readUTF();
                            //ветвление на случай отключения от чата
                            if (str.equals("/end")) {
                                //информация об отключении дублируется на сервер
                                out.writeUTF("/end");
                                break;
                            }
                            server.broadcastMsg(this,str);

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
}
