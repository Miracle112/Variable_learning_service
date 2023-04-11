package com.example.variable_learning_service;

import javafx.util.Pair;

import java.sql.*;

public class DBHandler {
    Statement statement;
    Connection dbConnection;
    ResultSet res;

    public Connection getDBConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://127.0.0.1:3306/variable_learning_service";

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, "root", "123558800Kent");
        return dbConnection;
    }

    public Pair<Integer, String> getRole(String login, String password) {
        try {
            statement = getDBConnection().createStatement();
            res = statement.executeQuery("SELECT * FROM users WHERE login = '" + login + "' AND password = '" + password + "'");
            res.next();
            return new Pair<>(res.getInt("id_users"), res.getString("id_role"));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkParentDataIsNotNull(int userId) {
        try {
            statement = getDBConnection().createStatement();
            res = statement.executeQuery("SELECT * FROM parents_data WHERE id_user = '" + userId + "'");
            return res.next();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void registration(String name, String surname, String patronymic, Integer id_role, String login, String password) {
        String insert = "INSERT INTO users(name, surname, patronymic, id_role, login, password)"
                + " VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement peSt = getDBConnection().prepareStatement(insert);
            peSt.setString(1, name);
            peSt.setString(2, surname);
            peSt.setString(3, patronymic);
            peSt.setInt(4, id_role);
            peSt.setString(5, login);
            peSt.setString(6, password);
            peSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//    public void send_appeals(Integer id_user,String name_child, String surname_child, String patronymic_child, Integer id_role, String login, String password) {
//        String insert = "INSERT INTO users(name, surname, patronymic, id_role, login, password)"
//                + " VALUES(?,?,?,?,?,?)";
//        try {
//            PreparedStatement peSt = getDBConnection().prepareStatement(insert);
//            peSt.setString(1, name);
//            peSt.setString(2, surname);
//            peSt.setString(3, patronymic);
//            peSt.setInt(4, id_role);
//            peSt.setString(5, login);
//            peSt.setString(6, password);
//            peSt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    public ResultSet querry(String querry) {
        try{
            Statement statement = getDBConnection().createStatement();
            res = statement.executeQuery(querry);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }
}