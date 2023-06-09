package com.example.variable_learning_service;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientController {
    int userId = AuthorizationController.id;
    String name;
    String surname;

    @FXML
    private Button appealsBtn;

    @FXML
    private TableView<ObservableList> approvedAppealsTable;

    @FXML
    private Button infoKindergarten;

    @FXML
    private Label nameLbl;

    @FXML
    private Button back;

    @FXML
    void initialize() {

        DBHandler dbHandler = DBHandler.getInstance();
        ResultSet resultSet = dbHandler.querry("SELECT name, surname FROM users WHERE id_users = '" + userId + "';");
        try {
            resultSet.next();
            name = resultSet.getString("name");
            surname = resultSet.getString("surname");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        nameLbl.setText("Здравствуйте " + name + " " + surname);
        back.setOnAction(event -> {
            Main.open("/com/example/variable_learning_service/authorization.fxml", back, "Авторизация");
        });

        infoKindergarten.setOnAction(event -> {
            Main.open("/com/example/variable_learning_service/info_kindergarten.fxml", infoKindergarten, "Информация о садике");
        });

        appealsBtn.setOnAction(event -> {
            Main.open("/com/example/variable_learning_service/appeals.fxml", appealsBtn, "Написать обращение");
        });

        String requestAdmin = "SELECT kind_of_help as 'Вид помощи', date as 'Дата', time as 'Время', comment as 'Коментарий'," +
                " status as 'Статус' FROM variable_learning_service.appeals WHERE id_user = '" + userId + "';";
        try {
            Main.fill(requestAdmin, approvedAppealsTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



}