package Server;

import Chat.Message;
import Chat.MessageType;
import Names.UserName;
import Server.Commands.IServerCommand;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ListIterator;
import java.util.logging.Logger;

public class RequestHandler implements Runnable {
    private static final Logger logger = Logger.getLogger(RequestHandler.class.getName());
    private Socket socket;
    private Server server;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    private UserName userName;

    public RequestHandler(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    public void close() {
        try {
            socket.close();
            writer.close();
            reader.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void sendMessage(Message message) {
        try {
            writer.writeObject(message);
            writer.flush();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public UserName getUserName() {
        return userName;
    }

    @Override
    public void run() {
        try {
            writer = new ObjectOutputStream(socket.getOutputStream());
            reader = new ObjectInputStream(socket.getInputStream());

            boolean isSuccessLogin = false;
            while (!isSuccessLogin) {
                Message loginMessage = (Message) reader.readObject();
                if (loginMessage.getMessageType() != MessageType.SERVER_REQUEST) {
                    sendMessage(new Message("Invalid request", MessageType.SERVER_RESPONSE));
                    continue;
                }
                userName = loginMessage.getSenderName();
                isSuccessLogin = server.registerNewUser(userName, this);
                if (!isSuccessLogin) {
                    if (server.isLogging()) {
                        logger.warning("User name is taken: " + userName.getName());
                    }
                    sendMessage(new Message("User name is taken", MessageType.SERVER_RESPONSE));
                }
                else {
                    ListIterator<Message> iterator = server.getStoredMessages().listIterator(server.getStoredMessages().size());
                    logger.info("Sending stored messages");
                    while (iterator.hasPrevious()) {
                        sendMessage(iterator.previous());
                    }
                }
            }

            server.broadcastMessage(new Message("User connected: " + userName.getName(), MessageType.GENERAL_MESSAGE));
            logger.info("Broadcast message of connection of user: " + userName.getName());

            while (!socket.isClosed()) {
                Message message = (Message) reader.readObject();
                logger.info("Received message from " + userName.getName());
                IServerCommand serverCommand = server.getCommand(message.getMessageType());
                serverCommand.execute(server, message, this);
            }


        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            close();
        }
    }
}
