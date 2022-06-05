package Server.Commands;

import Chat.Message;
import Server.Server;
import Server.RequestHandler;

public interface IServerCommand {
    void execute(Server server, Message message, RequestHandler requestHandler);
}
