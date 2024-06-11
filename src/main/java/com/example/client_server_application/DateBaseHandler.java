package com.example.client_server_application;

import java.sql.*;

public class DateBaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost/coursework";
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
}
