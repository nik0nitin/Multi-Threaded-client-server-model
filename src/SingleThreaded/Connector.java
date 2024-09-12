package SingleThreaded;
public class Connector {
    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        for(int i=0; i<10000; i++){
            Client client = new Client();
            client.run();
        }
        long end=System.currentTimeMillis();
        System.out.println("time taken: " + (end - start));
    }
}
