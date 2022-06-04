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
        if (message.getMessageData().equals("/exit")) {
            server.disconnect(requestHandler.getUserName());
            requestHandler.sendMessage(new Message(message.getMessageData(), MessageType.SERVER_RESPONSE));
            if (server.isLogging()) {
                logger.info(requestHandler.getUserName().getName() + " disconnected");
            }
            server.broadcastMessage(new Message(requestHandler.getUserName().getName() + " disconnected (" + message.getDate() + ")" , MessageType.GENERAL_MESSAGE));
        }
        else if (message.getMessageData().equals("/users")) {
            requestHandler.sendMessage(new Message("All connected users: " + System.lineSeparator() + server.getUsers(), MessageType.GENERAL_MESSAGE));
        }
        else {
            requestHandler.sendMessage(new Message("Unknown command: " + message.getMessageData(), MessageType.GENERAL_MESSAGE));
        }
    }
}
