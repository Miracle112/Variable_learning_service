package com.example.variable_learning_service;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class infoKindergartenController {

    @FXML
    private Button back;

    @FXML
    private TextField fieldAddress;

    @FXML
    private TextField fieldFullName;

    @FXML
    private TextField fieldTime;

    @FXML
    private Label nameKindergarten;

    @FXML
    void initialize() {

        back.setOnAction(event -> {
            open("/com/example/variable_learning_service/client.fxml", back, "Личный кабинет");
        });
    }

    private void open(String path, Button button, String title) {
        button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene((new Scene(root)));
        stage.setTitle(title);
        stage.show();
    }
}