package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import Chat.*;
import Names.UserName;
import Viewer.UserNameAsker;

public class Client implements Runnable, Observable {
    private final String ip;
    private final int port;
    private Socket socket;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    private Chat chat;
    private final Set<Observer> observers;
    private Message receivedMessage;
    private UserName userName;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
        observers = new HashSet<Observer>();
    }

    public UserName getUserName() {
        return userName;
    }

    public void setUserName(UserName userName) {
        this.userName = userName;
    }

    public boolean isConnected() {
        return !socket.isClosed();
    }

    public void close() {
        try {
            writer.close();
            socket.close();
            reader.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void sendMessage(Message message) throws IOException {
        if (message.getMessageData().indexOf("/") == 0) {
            message.setMessageType(MessageType.SERVER_REQUEST);
        }
        writer.writeObject(message);
        writer.flush();
        if (message.getMessageData().equals("/exit")) {
            close();
        }
    }

    public void run() {
        try {
            socket = new Socket(ip, port);
            writer = new ObjectOutputStream(socket.getOutputStream());
            reader = new ObjectInputStream(socket.getInputStream());
            chat = new Chat();
            Thread thread = new Thread(new ClientController(this));

            UserNameAsker userNameAsker = new UserNameAsker();
            userName = userNameAsker.ask();
            Message loginMessage = new Message("Hello", MessageType.SERVER_REQUEST);
            loginMessage.setSenderName(userName);
            sendMessage(loginMessage);

            thread.start();
            while (isConnected()) {
                receivedMessage = (Message) reader.readObject();
                if (receivedMessage.getMessageType() == MessageType.GENERAL_MESSAGE) {
                    chat.addMessage(receivedMessage);
                    System.out.println(receivedMessage.getMessageData());
                }
                else if (receivedMessage.getMessageType() == MessageType.SERVER_RESPONSE) {
                    if (receivedMessage.getMessageData().equals("Invalid request")) {
                        loginMessage = new Message("Hello", MessageType.SERVER_REQUEST);
                        loginMessage.setSenderName(userName);
                        sendMessage(loginMessage);
                    }
                    else if (receivedMessage.getMessageData().equals("User name is taken")) {
                        userName = userNameAsker.ask();
                        loginMessage.setSenderName(userName);
                        sendMessage(loginMessage);
                    }
                    else if (receivedMessage.getMessageData().equals("/exit")) {
                        System.err.println("Exiting");
                        break;
                    }
                    else {
                        chat.addMessage(receivedMessage);
                    }
                }
                notifyObservers();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            close();
        }
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
}
