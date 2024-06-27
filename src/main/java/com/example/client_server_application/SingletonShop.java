package com.example.client_server_application;

public class SingletonShop {
    private static SingletonShop instance = null;
    private int shopId;

    private SingletonShop() {
    }

    public static SingletonShop getInstance() {
        if (instance == null) {
            instance = new SingletonShop();
        }
        return instance;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getShopId() {
        return this.shopId;
    }
}
