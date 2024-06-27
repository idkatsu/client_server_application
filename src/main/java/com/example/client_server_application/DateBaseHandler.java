package com.example.client_server_application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DateBaseHandler extends Configs {
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
        String insert = "INSERT INTO users (firstName, lastName, userName, password) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getUserName());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.executeUpdate();
    }

    public ResultSet getUser(User user) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + Const.USERS_TABLE + " WHERE " +
                Const.USERS_USERNAME + "=? AND " + Const.USERS_PASSWORD + "=?"; // =? - какое-то значение
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getPassword());

        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public void changePasswordByUsername(String username, String newPassword) throws SQLException, ClassNotFoundException {
        String update = "UPDATE " + Const.USERS_TABLE + " SET " + Const.USERS_PASSWORD + "=? WHERE " + Const.USERS_USERNAME + "=?";
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
        preparedStatement.setString(1, rent.getClient_id());
        preparedStatement.setString(2, rent.getBicycle_id());
        preparedStatement.setString(3, rent.getShop_id());
        preparedStatement.setString(4, rent.getDate_receipt());
        preparedStatement.setString(5, rent.getDate_return());
        preparedStatement.executeUpdate();
    }

    public void addIdClients(Rent rent) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO rents (client_id) VALUES (?)";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
        preparedStatement.setString(1, rent.getClient_id());
        preparedStatement.executeUpdate();
    }

    public int getUserIdByLogin(String login) throws SQLException, ClassNotFoundException {
        String query = "SELECT idUsers FROM users WHERE iserName = ?";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setString(1, login);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("idUsers");
        }

        return -1;
    }
}
