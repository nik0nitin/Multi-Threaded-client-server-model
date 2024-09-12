package SingleThreaded;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Client {

	public static void main(String[] args) {
	   Client client=new Client();
		client.run();
	}

    public void run(){
        int port = 8090;
        Console con=System.console();
        try {
            InetAddress address = InetAddress.getByName("localhost");
            Socket socket = new Socket(address, port);

            PrintWriter fromClient = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String input = "Hello from client side.";
            fromClient.println(input);
            String line = fromServer.readLine();
            System.out.println("Server: " + line);

            fromClient.close();
            fromServer.close();
            //socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
