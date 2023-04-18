package com.example.variable_learning_service;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AppealsContrller {

    @FXML
    private TextField kind_of_help;

    @FXML
    private TextField name;

    @FXML
    private TextField name_child;

    @FXML
    private TextField patronymic;

    @FXML
    private TextField patronymic_child;

    @FXML
    private Button sendAppeal;

    @FXML
    private TextField surname;

    @FXML
    private TextField surname_child;

    @FXML
    void initialize() {
        DBHandler dbHandler = DBHandler.getInstance();

        sendAppeal.setOnAction(event -> {
            // запрос на создание обращения в бд
            Main.open("/com/example/variable_learning_service/client.fxml", sendAppeal, "Личный кабинет");
        });




    }


}