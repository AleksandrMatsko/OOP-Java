package Server.Commands;

import Chat.Message;
import Chat.MessageType;
import Server.Server;
import Server.RequestHandler;

import java.util.logging.Logger;

public class ServerRequestCommand implements IServerCommand {
    private final static Logger logger = Logger.getLogger(ServerRequestCommand.class.getName());

    @Override
    public void execute(Server server, Message message, RequestHandler requestHandler) {
        if (message.getMessageType() != MessageType.SERVER_REQUEST) {
            return;
        }
        requestHandler.sendMessage(new Message(message.getMessageData(), MessageType.GENERAL_MESSAGE, message.getSenderName()));
        if (message.getMessageData().equals("/exit")) {
            server.disconnect(requestHandler.getUserName());
            if (server.isLogging()) {
                logger.info(requestHandler.getUserName().getName() + " disconnected");
            }
            server.broadcastMessage(new Message(requestHandler.getUserName().getName() + " disconnected",
                    MessageType.GENERAL_MESSAGE, server.getServerName()));
        }
        else if (message.getMessageData().equals("/users")) {
            requestHandler.sendMessage(new Message(System.lineSeparator() + "All connected users: " +
                    System.lineSeparator() + server.getUsers(), MessageType.GENERAL_MESSAGE, server.getServerName()));
        }
        else {
            requestHandler.sendMessage(new Message("Unknown command: " + message.getMessageData(),
                    MessageType.GENERAL_MESSAGE, server.getServerName()));
        }
    }
}
