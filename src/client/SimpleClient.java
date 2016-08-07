package client;

import java.io.*;
import java.net.Socket;

/**
 * Class/Interface purpose.
 *
 * @author vzateychuk
 */
public class SimpleClient extends Thread {
    private final int id;
    private final int port;
    private final String host;


    public SimpleClient(int id, String host, int port){
        this.id = id;
        this.host = host;
        this.port = port;
        System.out.println("Created client("+id+"), host="+host+":"+port);
    }

    public void run(){
        System.out.println("Run client("+id+")");

        try
        {
            // открываем сокет и коннектимся к localhost
            // получаем сокет сервера
            Socket socket = new Socket(host, port);

            // берём поток вывода и выводим туда первый аргумент
            // заданный при вызове, адрес открытого сокета и его порт
            String[] messages = {"Client("+id+") Hello!",
                                "Client("+id+") speaking",
                                "Client("+id+") рад с вами пообщаться.",
                                "Client("+id+"), ip info: " + socket.getInetAddress().getHostAddress() +":"+socket.getLocalPort(),
                                "Client("+id+") Передавайте всем привет.",
                                "Bue."};

            // буферизированный поток от клиента к серверу (out) и поток данных от сервера к клиенту (in)
            PrintWriter out  = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // в цикле отправляем на сервер сообщения одно за другим
            for (String message: messages) {
                // отправляем строку на сервер
                out.println(message);
                // и читаем ответ
                String data = in.readLine();
                // выводим ответ в консоль
                System.out.println("Client("+id+"), server responded="+data);
            }

        }
        catch(Exception e)
        {System.out.println("Client("+id+") thread init error: "+e);} // вывод исключений
    }
}
