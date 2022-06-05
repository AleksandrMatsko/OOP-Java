import Client.Client;
import Viewer.Viewer;

public class ClientMain {
    public static void main(String[] args) {
        Client client = new Client("192.168.1.13", 12345);
        client.addObserver(new Viewer(client));
        client.run();
    }
}
