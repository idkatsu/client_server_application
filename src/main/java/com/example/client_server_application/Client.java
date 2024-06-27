package com.example.client_server_application;

public class Client {
    public String name;
    public String passport;
    public String address;

    public Client(String name, String passport, String address) {
        this.name = name;
        this.passport = passport;
        this.address = address;
    }

    public Client() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getName() {
        return name;
    }

    public String getPassport() {
        return passport;
    }

    public String getAddress() {
        return address;
    }
}
