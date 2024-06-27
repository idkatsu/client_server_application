package com.example.client_server_application;

public class SingletonBicycle {
    private static SingletonBicycle instance = null;
    private int bicycleId;

    private SingletonBicycle() {
    }

    public static SingletonBicycle getInstance() {
        if (instance == null) {
            instance = new SingletonBicycle();
        }
        return instance;
    }

    public void setBicycleId(int bicycleId) {
        this.bicycleId = bicycleId;
    }

    public int getBicycleId() {
        return this.bicycleId;
    }

}
