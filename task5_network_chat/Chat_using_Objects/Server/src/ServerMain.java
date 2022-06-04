import Server.Server;

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ServerMain {
    private static final Logger logger = Logger.getLogger(ServerMain.class.getName());

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = new Server();
        boolean isLogging = server.isLogging();
        if (isLogging) {
            LogManager.getLogManager().readConfiguration(ServerMain.class.getResourceAsStream("/logging.properties"));
        }
        Thread serverThread = new Thread(server);
        serverThread.start();
        serverThread.join();
    }
}
