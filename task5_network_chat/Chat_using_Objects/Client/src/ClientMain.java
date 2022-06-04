import Client.Client;

public class ClientMain {
    public static void main(String[] args) throws InterruptedException {
        Client client = new Client("127.0.0.1", 12345);
        Thread thread = new Thread(client);
        thread.start();
        thread.join();
    }
}
