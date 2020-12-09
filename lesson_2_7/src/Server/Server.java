package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
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
        authService = new SimpleAuthService();

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
        String message = String.format("%s : %s", sender.getNickname(), msg);
        //итератор пробегается по всем клиентам и рассыллает сообщение
        for (ClientHandler client : clients) {
            client.sendMsg(message);
        }


    }
    //метод добавления клиента (подписки)
    public void subscribe(ClientHandler clientHandler){
        clients.add(clientHandler);
    }

    //метод исключения клиента (отписки)
    public void unsubscribe(ClientHandler clientHandler){
        clients.remove(clientHandler);
    }


}
