package MultiThreaded;

public class Connector {
    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        Client client=new Client();
        client.run();
        long end=System.currentTimeMillis();
        System.out.println("time taken: " + (end - start));
    }
}

