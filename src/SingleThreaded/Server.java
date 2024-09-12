package SingleThreaded;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    private static int count =1;

    public void run() throws IOException {
        int port=8090;
        String ip="localhost";
        Console con=System.console();
        ServerSocket server= new ServerSocket(port);

            while(true){
                System.out.println("Server is listening on port " + port);
                Socket clientSocket=server.accept();
                System.out.println("Connection made successfully " + clientSocket.getRemoteSocketAddress());

                //Server is outputStream, client is inputStream;
                PrintWriter fromServer = new PrintWriter(clientSocket.getOutputStream(), true);
                InputStreamReader isr=new InputStreamReader(clientSocket.getInputStream());
                BufferedReader fromClient= new BufferedReader(isr);

                String input="Hello from server side.";
                fromServer.println(input);
                String line = fromClient.readLine();
                System.out.println("Client: " + count++/*line*/);

                fromServer.close();
                fromClient.close();
                clientSocket.close();
            }

    }

    public static void main(String[] args) {
        Server server=new Server();
        try{
            server.run();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
