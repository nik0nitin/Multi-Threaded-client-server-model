package MultiThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private static int count=1;
    public static void main(String[] args) {
        Client client=new Client(); //will be used for our method
        int totalThreads= Integer.parseInt(args[0]);
        for(int i=0; i<totalThreads; i++){
            try{
                Thread thread=new Thread(client.getRunnable());
                thread.start();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public Runnable getRunnable(){
        return new Runnable() {
            @Override
            public void run() {
                int port=8090;
                try {
                    InetAddress address=InetAddress.getByName("localhost");
                    Socket socket = new Socket(address, port);

                    PrintWriter fromClient = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader fromServer= new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    fromClient.println("Hello from client " + count++);
                    String line = fromServer.readLine();
                    System.out.println("Server: " + line);

                    fromClient.close();
                    fromServer.close();
                    socket.close();

                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
