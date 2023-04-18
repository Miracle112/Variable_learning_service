package com.example.variable_learning_service;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

        String requestAdmin = "SELECT id_accounting, (select name from burtases.trainer where id_trainer = accounting.id_trainer) as 'Имя', (select firstname from burtases.trainer where id_trainer = accounting.id_trainer) as 'Фамилия', salary as 'Зарплата' FROM burtases.accounting";
        try {
            Main.fill(requestAdmin, admTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        delBtn.setOnAction(event -> {
            ObservableList id_del = admTable.getSelectionModel().getSelectedItem();
            Object id_del_index = id_del.get(0);
            String delTrainer = "DELETE FROM trainer WHERE(id_trainer = " + id_del_index + ")";
            String delAdminTrainers = "DELETE FROM accounting WHERE(id_trainer = " + id_del_index + ")";
            try {
                PreparedStatement prSt1 = dbHandler.getDBConnection().prepareStatement(delTrainer);
                PreparedStatement prSt2 = dbHandler.getDBConnection().prepareStatement(delAdminTrainers);
                prSt1.executeUpdate();
                prSt2.executeUpdate();
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
            Main.open("/com/example/variable_learning_service/authorization.fxml", exitBtn, "Регистрация");
        });

        addBtn.setOnAction(actionEvent -> {
            addBtn.getScene().getWindow().hide();
            Main.open("/com/example/variable_learning_service/addTrainer.fxml", addBtn, "Добавить тренера");
        });
    }

}