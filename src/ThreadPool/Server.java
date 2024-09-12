package ThreadPool;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static short count=(short) 1;
    private final ExecutorService threadPool;

    public Server(int poolsize){
        this.threadPool= Executors.newFixedThreadPool(poolsize);
    }

    public static void main(String[] args) {
        int port = 8090;
        int poolsize=9000;
        Server server=new Server(poolsize);
        try {
            ServerSocket serverSocket=new ServerSocket(port);
            System.out.println("Server is listenting on port: " + port);
            while(true){
                Socket clientSocket=serverSocket.accept();
                server.getThreadPool().execute(() -> server.handleClient(clientSocket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ExecutorService getThreadPool() {
        return threadPool;
    }

    public void handleClient(Socket clientSocket){
        try {
            PrintWriter fromServer=new PrintWriter(clientSocket.getOutputStream(), true);
            fromServer.println("Hello from server to the world of warcraft.");

            BufferedReader fromClient=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String line=fromClient.readLine();
            System.out.println("Client: " + count++);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
