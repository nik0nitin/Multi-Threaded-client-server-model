package MultiThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {
    private static int count=1;

    public Consumer<Socket> getConsumer(){
        return (clientSocket) -> {
            try {
                PrintWriter fromServer = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader fromClient=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                fromServer.println("Hello from server to the world of warcraft." /*+*/ /*count++*/);
                String line = fromClient.readLine();
                System.out.println("Client: " + count++);

                fromServer.close();
                fromClient.close();
                clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    public static void main(String[] args) {
        int port = 8090;
        ServerSocket serverSocket;
        Server server=new Server();

        try {
            serverSocket = new ServerSocket(port);
//            serverSocket.setSoTimeout(1000 * 10);
            System.out.println("Server is running on port: " + port);
            while (true) {
                Socket acceptedSocket=serverSocket.accept();
                Thread thread=new Thread(() -> server.getConsumer().accept(acceptedSocket));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
