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
            Main.open("/com/example/variable_learning_service/client.fxml", back, "Личный кабинет");
        });
    }

}