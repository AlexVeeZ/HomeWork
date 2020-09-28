package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class Server {

    //создаем список клиентов
    private List<ClientHandler> clients;

    //реализуем интерфейс AuthService
    private AuthService authService;

    //вводим поле с номером порта
    private int PORT = 8189;
    //инициализируем объекты серверного и клиентского сокета
    ServerSocket server = null;
    Socket socket = null;

    public Server() {

        //инициализируем список через потокобезопасную реализацию List : Vector
        clients = new Vector<>();

        //реализуем интерфейс AuthService
        //authService = new SimpleAuthService();
        if (!SQLHandler.connect()) {
            throw new RuntimeException("Не удалось подключиться к БД");
        }
        authService = new DBAuthService();

        try{
            //привязываем номер порта к серверному сокету
            //сообщаем о запуске сервера
            server = new ServerSocket(PORT);
            System.out.println("Сервер запущен");


            //запуск постоянного цикла для переписки
            while(true){
                //сервер ожидает подключение клиента
                //сообщаем о подключении клиента
                socket = server.accept();
                System.out.println("Клиент подключился");
                //добавляем нового клиента (ниже описана реализация метода subscribe
                //в список и передаем его параметры в ClientHandler
                new ClientHandler(this,socket);

            }


        }catch (IOException e){
            e.printStackTrace();
        }finally {
            SQLHandler.disconnect();
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    //метод рассылки сообщения по всем клиентам
    public void broadcastMsg(ClientHandler sender, String msg){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        format.format(date);

        String message = String.format(" %s %s : %s",format.format(date), sender.getNickname(), msg);
        SQLHandler.addMessage(sender.getNickname(),"null",msg, format.format(new Date()));

        //итератор пробегается по всем клиентам и рассыллает сообщение
        for (ClientHandler client : clients) {
            client.sendMsg(message);
        }


    }
    //метод добавления клиента (подписки)
    public void subscribe(ClientHandler clientHandler){

        clients.add(clientHandler);
        broadcastClientList();
    }

    //метод исключения клиента (отписки)
    public void unsubscribe(ClientHandler clientHandler){

        clients.remove(clientHandler);
        broadcastClientList();
    }

    //метод приватной отправки сообщения
    public void privateMsg(ClientHandler sender,String receiver, String msg){
        String message = String.format("[%s] private [%s] : %s", sender.getNickname(),receiver, msg);
        //итератор пробегается по всем клиентам и рассыллает сообщение
        for (ClientHandler client : clients) {
            if(client.getNickname().equals(receiver)){
                client.sendMsg(message);

                SQLHandler.addMessage(sender.getNickname(),receiver,msg,">>>");

                if(!client.equals(sender)) {
                    sender.sendMsg(message);
                }
                return;
            }

        }
        sender.sendMsg("user not found: "+receiver);

    }

    public boolean isLoginAuthenticated(String login){
        for(ClientHandler c : clients){
            if(c.getLogin().equals(login)){
                return true;
            }

        }
        return false;

    }
    void broadcastClientList(){
        StringBuilder sb = new StringBuilder("/clientlist ");
        for (ClientHandler c : clients) {
            sb.append(c.getNickname()).append(" ");
        }
        String msg = sb.toString();
        for (ClientHandler c : clients) {
            c.sendMsg(msg);
        }

    }

}
