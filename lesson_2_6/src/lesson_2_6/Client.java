package lesson_2_6;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {


    private final String IP_ADDRESS = "localhost";
    private final int PORT = 8189;

    private Socket socket;
    PrintWriter writer;
    BufferedReader reader;


    public void workClient(){

        try {
            socket = new Socket(IP_ADDRESS, PORT);
            writer = new PrintWriter(socket.getOutputStream());
            InputStreamReader in = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(in);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            Scanner sc1 = new Scanner(System.in);
                            String str = sc1.nextLine();
                            writer.println(str);
                            writer.flush();
                            if (str.equalsIgnoreCase("/end")) break;
                        }
                    } finally {
                        try {
                            socket.close();
                            System.exit(0);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str2 = reader.readLine();
                            System.out.println("Сервер: " + str2);
                            // if (str2.equalsIgnoreCase("/end")) break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();

                    } finally {
                        try {
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

}
