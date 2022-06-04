package Server;

import Chat.*;
import Exceptions.InvalidUserNameException;
import Names.UserName;
import Server.Commands.GeneralMessageCommand;
import Server.Commands.IServerCommand;
import Server.Commands.ServerRequestCommand;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class Server implements Runnable {
    private static final Logger logger = Logger.getLogger(Server.class.getName());

    private int port;
    private ServerSocket serverSocket;
    private ConcurrentHashMap<UserName, RequestHandler> clients;
    private ConcurrentHashMap<MessageType, IServerCommand> serverCommands;
    private int numOfShowingMessages;
    private final Chat chat;
    private boolean isLogging;
    private final UserName serverName;


    public Server() {
        Properties properties = new Properties();
        try {
            properties.load(Server.class.getResourceAsStream("/server.properties"));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            serverName = new UserName(properties.getProperty("ServerName"));
        }
        catch (InvalidUserNameException ex) {
            throw new RuntimeException("Invalid server name");
        }
        port = Integer.parseInt(properties.getProperty("Port"));
        numOfShowingMessages = Integer.parseInt(properties.getProperty("NumShowingMessages"));
        clients = new ConcurrentHashMap<UserName, RequestHandler>();
        chat = new Chat(numOfShowingMessages);
        isLogging = Boolean.parseBoolean(properties.getProperty("Logging"));
        serverCommands = new ConcurrentHashMap<>();
        serverCommands.put(MessageType.GENERAL_MESSAGE, new GeneralMessageCommand());
        serverCommands.put(MessageType.SERVER_REQUEST, new ServerRequestCommand());
    }

    public boolean isLogging() {
        return isLogging;
    }

    public UserName getServerName() {
        return serverName;
    }

    public void disconnect(UserName userName) {
        if (!clients.containsKey(userName)) {
            //TODO exception
        }
        clients.get(userName).sendMessage(new Message("/exit", MessageType.SERVER_RESPONSE, serverName));
        clients.get(userName).close();
        clients.remove(userName);
    }

    public String getUsers() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<UserName, RequestHandler> entry : clients.entrySet()) {
            stringBuilder.append(entry.getKey().getName());
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    public boolean registerNewUser(UserName userName, RequestHandler requestHandler) {
        if (clients.containsKey(userName) || userName.equals(serverName)) {
            return false;
        }
        clients.put(userName, requestHandler);
        return true;
    }

    public IServerCommand getCommand(MessageType messageType) {
        if (!serverCommands.containsKey(messageType)) {
            //TODO exception
        }
        return serverCommands.get(messageType);
    }

    private void addMessage(Message message) {
        synchronized (chat) {
            chat.addMessage(message);
        }
    }

    public void broadcastMessage(Message message) {
        addMessage(message);
        for (Map.Entry<UserName, RequestHandler> entry : clients.entrySet()) {
            entry.getValue().sendMessage(message);
        }
    }

    public LinkedList<Message> getStoredMessages() {
        LinkedList<Message> messages;
        synchronized (chat) {
            messages = chat.getMessageList();
        }
        return messages;
    }

    public void close() {
        try {
            serverSocket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            logger.info("Server started on port: " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                if (isLogging) {
                    logger.info("Client connected " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
                }
                Thread thread = new Thread(new RequestHandler(this, clientSocket));
                thread.start();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            close();
        }
    }

    public void stop() {
        for (Map.Entry<UserName, RequestHandler> entry : clients.entrySet()) {
            disconnect(entry.getKey());
        }
        close();
    }
}
