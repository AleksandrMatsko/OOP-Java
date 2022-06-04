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
    private final Chat chat;
    private final Set<Observer> observers;
    private Message receivedMessage;
    private UserName userName;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
        observers = new HashSet<Observer>();
        chat = new Chat();
    }

    public UserName getUserName() {
        return userName;
    }

    public void setUserName(UserName userName) {
        this.userName = userName;
    }

    public boolean isConnected() {
        if (socket == null) {
            return false;
        }
        return !socket.isClosed();
    }

    public void close() {
        if (!isConnected()) {
            return;
        }
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

    public void connect() {
        try {
            socket = new Socket(ip, port);
            writer = new ObjectOutputStream(socket.getOutputStream());
            reader = new ObjectInputStream(socket.getInputStream());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void run() {
        try {
            connect();
            if (socket == null || !isConnected()) {
                return;
            }

            UserNameAsker userNameAsker = new UserNameAsker();
            userName = userNameAsker.ask();
            Message loginMessage = new Message("Hello", MessageType.SERVER_REQUEST, userName);
            sendMessage(loginMessage);

            while (isConnected()) {
                receivedMessage = (Message) reader.readObject();
                if (receivedMessage.getMessageType() == MessageType.GENERAL_MESSAGE) {
                    chat.addMessage(receivedMessage);
                }
                else if (receivedMessage.getMessageType() == MessageType.SERVER_RESPONSE) {
                    if (receivedMessage.getMessageData().equals("Invalid request")) {
                        loginMessage = new Message("Hello", MessageType.SERVER_REQUEST, userName);
                        sendMessage(loginMessage);
                    }
                    else if (receivedMessage.getMessageData().equals("User name is taken")) {
                        userName = userNameAsker.ask();
                        loginMessage = new Message("Hello", MessageType.SERVER_REQUEST, userName);
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
            notifyObservers();
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

    public Message getReceivedMessage() {
        return receivedMessage;
    }
}
