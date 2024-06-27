package com.example.client_server_application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DateBaseHandler {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost/coursework3";
        String username = "root";
        String password = "";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(url, username, password);
        return dbConnection;
    }

    // sql запрос
    public void signUpUser(User user)
            throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO users (userName, password) VALUES (?,?)";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.executeUpdate();
    }

    public void addClients(Client client) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO clients (name, passport, address) VALUES (?,?,?)";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
        preparedStatement.setString(1, client.name);
        preparedStatement.setString(2, client.passport);
        preparedStatement.setString(3, client.address);
        preparedStatement.executeUpdate();
    }

    public void fetchClientId(String clientName) throws SQLException, ClassNotFoundException {
        String select = "SELECT id FROM clients WHERE name = ?";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
        preparedStatement.setString(1, clientName);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int clientId = resultSet.getInt("id");
            SingletonClient.getInstance().setClientId(clientId);
            SingletonClient.getInstance().setClientId(clientId);
        } else {
            System.out.println("Клиент с именем " + clientName + " не найден.");
        }
    }


    public ResultSet getUser(User user) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + "users" + " WHERE " +
                "userName" + "=? AND " + "password" + "=?"; // =? - какое-то значение
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getPassword());

        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public void changePasswordByUsername(String username, String newPassword) throws SQLException, ClassNotFoundException {
        String update = "UPDATE " + "users" + " SET " + "password" + "=? WHERE " + "userName" + "=?";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(update);
        preparedStatement.setString(1, newPassword);
        preparedStatement.setString(2, username);

        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Пароль успешно изменен.");
        } else {
            System.out.println("Ошибка: Не удалось изменить пароль. Пользователь с таким логином не найден.");
        }

    }

    public List<Model_bicycle> getBicycles() throws SQLException, ClassNotFoundException {
        List<Model_bicycle> bicycles = new ArrayList<>();

        String select = "SELECT * FROM models_bicycle";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String type = resultSet.getString("type");
            String number_of_gears = resultSet.getString("num_of_gears");

            Model_bicycle bicycle = new Model_bicycle(name, type, number_of_gears);
            bicycles.add(bicycle);
        }

        return bicycles;
    }

    public List<Shop> getMagazin() throws SQLException, ClassNotFoundException {
        List<Shop> shops = new ArrayList<>();

        String select = "SELECT * FROM shops";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            Shop shop = new Shop(name, address);
            shops.add(shop);
        }

        return shops;
    }

    public void addRent(Rent rent) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO rents (client_id, bicycle_id, shop_id, date_receipt, date_return) VALUES (?,?,?,?,?)";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
        preparedStatement.setInt(1, SingletonClient.getInstance().getClientId());
        preparedStatement.setInt(2, SingletonBicycle.getInstance().getBicycleId());
        preparedStatement.setInt(3, SingletonShop.getInstance().getShopId());
        preparedStatement.setString(4, rent.getDate_receipt());
        preparedStatement.setString(5, rent.getDate_return());
        preparedStatement.executeUpdate();
    }
}
