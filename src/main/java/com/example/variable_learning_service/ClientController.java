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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientController {

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

        back.setOnAction(event -> {
            Main.open("/com/example/variable_learning_service/authorization.fxml", back, "Авторизация");
        });

        nameLbl.setText("Здравуствуйте Макисм Китанин");
        infoKindergarten.setOnAction(event -> {
            Main.open("/com/example/variable_learning_service/infoKindergarten.fxml", infoKindergarten, "Информация о садике");
        });

        appealsBtn.setOnAction(event -> {
            Main.open("/com/example/variable_learning_service/appeals.fxml", appealsBtn, "Написать обращение");
        });

        String requestAdmin = "SELECT * FROM variable_learning_service.appeals";
        try {
            Main.fill(requestAdmin, approvedAppealsTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



}