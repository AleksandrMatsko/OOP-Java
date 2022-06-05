package Server.Commands;

import Chat.Message;
import Server.Server;
import Server.RequestHandler;

import java.util.logging.Logger;

public class GeneralMessageCommand implements IServerCommand {

    @Override
    public void execute(Server server, Message message, RequestHandler requestHandler) {
        server.broadcastMessage(message);
    }
}
