package com.example.variable_learning_service;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminController {

    @FXML
    private Button addBtn;

    @FXML
    private TableView<ObservableList> admTable;

    @FXML
    private Button delBtn;

    @FXML
    private Button exitBtn;

    @FXML
    void initialize() {
        DBHandler dbHandler = DBHandler.getInstance();

        String requestAdmin = "SELECT * FROM variable_learning_service.users";
        try {
            Main.fill(requestAdmin, admTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        delBtn.setOnAction(event -> {
            ObservableList id_del = admTable.getSelectionModel().getSelectedItem();
            Object id_del_index = id_del.get(0);
            String delUsers = "DELETE FROM users WHERE(id_users = " + id_del_index + ")";
            try {
                PreparedStatement prSt = dbHandler.getDBConnection().prepareStatement(delUsers);
                prSt.executeUpdate();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            admTable.getItems().clear();
            try {
                Main.fill(requestAdmin, admTable);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        exitBtn.setOnAction(actionEvent -> {
            exitBtn.getScene().getWindow().hide();
            Main.open("/com/example/variable_learning_service/authorization.fxml", exitBtn, "Авторизация");
        });

        addBtn.setOnAction(actionEvent -> {
            addBtn.getScene().getWindow().hide();
            Main.open("/com/example/variable_learning_service/add_users.fxml", addBtn, "Добавить пользователя");
        });
    }

}