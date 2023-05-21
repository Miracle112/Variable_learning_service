package com.example.variable_learning_service;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.sql.SQLException;

public class ManagerController {

    @FXML
    private Button extBtn;

    @FXML
    private TableView<ObservableList> managerTable;

    @FXML
    private Button modBtn;

    @FXML
    void initialize() {

        String requestSpec = "SELECT id_appeals as 'Номер обращения', kind_of_help as 'Вид помощи', date as 'Дата', time as 'Время', comment as 'Коментарий'," +
                " (SELECT (SELECT CONCAT(name, ' ', surname, ' ', patronymic) FROM users WHERE users.id_users = specialists.id_user)" +
                " FROM specialists WHERE id_specialists = id_specialist) as 'Специалист', status as 'Статус' FROM variable_learning_service.appeals";
        try {
            Main.fill(requestSpec, managerTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        extBtn.setOnAction(actionEvent -> {
            extBtn.getScene().getWindow().hide();
            Main.open("/com/example/variable_learning_service/authorization.fxml", extBtn, "Авторизация");
        });

        modBtn.setOnAction(actionEvent -> {
            modBtn.getScene().getWindow().hide();
            Main.open("/com/example/variable_learning_service/appeal_processing.fxml", modBtn, "Обработка обращения");
        });

    }

}