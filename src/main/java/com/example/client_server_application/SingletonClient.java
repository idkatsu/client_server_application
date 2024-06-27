package com.example.client_server_application;

public class SingletonClient {
    private static SingletonClient instance = null;
    private int clientId;

    private SingletonClient() {
    }

    public static SingletonClient getInstance() {
        if (instance == null) {
            instance = new SingletonClient();
        }
        return instance;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getClientId() {
        return this.clientId;
    }
}
