package Handlers;


import Client.Client;

public abstract class ClientHandler {
    protected final Client client;

    public ClientHandler(Client client) {
        this.client = client;
    }

    public abstract void react();
}
