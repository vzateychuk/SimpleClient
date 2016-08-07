package main;

import client.SimpleClient;

import static java.lang.Thread.*;

public class Main {
    // адрес на котором работает сервер
    private final static String LOCALHOST = "localhost";
    // порт к которому будет подключаться клиент
    private final static int PORT = 5050;
    // количество клиентов которые будут создаваться и подключаться
    private final static int maxClients = 1000;

    public static void main(String[] args) throws InterruptedException {

        // Забираем адрес сервера для прослушивания как первое значение
        String serverAddress = LOCALHOST;
        if (args.length == 1) {serverAddress = args[0]; }

        for (int i=0; i<maxClients;i++) {
            SimpleClient client = new SimpleClient(i, serverAddress, PORT);
            client.start();
            sleep(1);
        }
    }
}
