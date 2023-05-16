package com.example.variable_learning_service;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecialistController {
    int userId = AuthorizationController.id;
    int id_specialists;

    @FXML
    private Button cancelBtn;

    @FXML
    private TableView<ObservableList> appealsTable;

    @FXML
    private Button closeBtn;

    @FXML
    private Button extBtn;

    @FXML
    void initialize() {
        DBHandler dbHandler = DBHandler.getInstance();
        ResultSet resultSet = dbHandler.querry("SELECT id_specialists FROM variable_learning_service.specialists WHERE id_user = '" + userId + "'");
        try {
            resultSet.next();
            id_specialists = resultSet.getInt("id_specialists");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String requestSpec = "SELECT id_appeals as 'Номер обращения', date as 'Дата', time as 'Время', " +
                "comment as 'Коментарий', status as 'Статус' FROM variable_learning_service.appeals WHERE id_specialist = '" + id_specialists + "'";
        try {
            Main.fill(requestSpec, appealsTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        extBtn.setOnAction(actionEvent -> {
            extBtn.getScene().getWindow().hide();
            Main.open("/com/example/variable_learning_service/authorization.fxml", extBtn, "Авторизация");
        });

        cancelBtn.setOnAction(actionEvent -> {
            ObservableList id_del = appealsTable.getSelectionModel().getSelectedItem();
            Object id_del_index = id_del.get(0);
            Integer id_appeals = Integer.parseInt(id_del_index +  "");
            dbHandler.closeAppeals("Отменена", id_specialists, id_appeals);
            cancelBtn.getScene().getWindow().hide();
            Main.open("/com/example/variable_learning_service/specialist.fxml", extBtn, "Специалист");
        });

        closeBtn.setOnAction(actionEvent -> {
            ObservableList id_del = appealsTable.getSelectionModel().getSelectedItem();
            Object id_del_index = id_del.get(0);
            Integer id_appeals = Integer.parseInt(id_del_index +  "");
            dbHandler.closeAppeals("Закрыта", id_specialists, id_appeals);
            cancelBtn.getScene().getWindow().hide();
            Main.open("/com/example/variable_learning_service/specialist.fxml", extBtn, "Специалист");
        });
    }
}
