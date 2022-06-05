package Server;

import Chat.Message;
import Chat.MessageType;
import Names.UserName;
import Server.Commands.IServerCommand;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.ListIterator;
import java.util.logging.Logger;

public class RequestHandler implements Runnable {
    private static final Logger logger = Logger.getLogger(RequestHandler.class.getName());
    private Socket socket;
    private Server server;
    private InputStream reader;
    private OutputStream writer;
    private UserName userName;

    public RequestHandler(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    public void close() {
        try {
            if (writer != null) {
                writer.close();
            }
            if (reader != null) {
                reader.close();
            }
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void sendMessage(Message message) {
        try {
            if (!socket.isClosed()) {
                byte[] packedMessage = message.packMessage();
                byte[] size = ByteBuffer.allocate(4).putInt(packedMessage.length).array();

                writer.write(size);
                writer.write(packedMessage);
                writer.flush();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Message receiveMessage() throws IOException {
        byte[] size = new byte[4];
        reader.read(size);
        int numBytes = ByteBuffer.wrap(size).getInt();
        byte[] packedMessage = new byte[numBytes];
        reader.read(packedMessage);
        Message receivedMessage = new Message();
        receivedMessage.unpackMessage(packedMessage);
        return receivedMessage;
    }

    public UserName getUserName() {
        return userName;
    }

    @Override
    public void run() {
        try {
            writer = socket.getOutputStream();
            reader = socket.getInputStream();

            boolean isSuccessLogin = false;
            while (!isSuccessLogin) {
                Message loginMessage = receiveMessage();
                if (loginMessage.getMessageType() != MessageType.SERVER_REQUEST) {
                    sendMessage(new Message("Invalid request", MessageType.SERVER_RESPONSE, server.getServerName()));
                    continue;
                }
                userName = loginMessage.getSenderName();
                isSuccessLogin = server.registerNewUser(userName, this);
                if (!isSuccessLogin) {
                    if (server.isLogging()) {
                        logger.warning("User name is taken: " + userName.getName());
                    }
                    sendMessage(new Message("User name is taken", MessageType.SERVER_RESPONSE, server.getServerName()));
                }
                else {
                    if (server.isLogging()) {
                        logger.info(userName.getName() + " login success");
                    }
                    ListIterator<Message> iterator = server.getStoredMessages().listIterator(server.getStoredMessages().size());
                    if (server.isLogging()) {
                        logger.info("Sending stored messages");
                    }
                    while (iterator.hasPrevious()) {
                        sendMessage(iterator.previous());
                    }
                }
            }

            server.broadcastMessage(new Message("User connected: " + userName.getName(), MessageType.GENERAL_MESSAGE, server.getServerName()));

            while (!socket.isClosed()) {
                Message message = receiveMessage();
                if (server.isLogging()) {
                    logger.info("Received message from " + userName.getName());
                }
                IServerCommand serverCommand = server.getCommand(message.getMessageType());
                if (serverCommand == null) {
                    throw new RuntimeException("Unknown type of message: " + message.getMessageType());
                }
                serverCommand.execute(server, message, this);
            }


        }
        catch (SocketException ex) {
            Message exitMessage = new Message("/exit", MessageType.SERVER_REQUEST, userName);
            IServerCommand serverCommand = server.getCommand(exitMessage.getMessageType());
            serverCommand.execute(server, exitMessage, this);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            close();
        }
    }
}
