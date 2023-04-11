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
        DBHandler dbHandler = new DBHandler();

        sendAppeal.setOnAction(event -> {
            // запрос на создание обращения в бд
            open("/com/example/variable_learning_service/client.fxml", sendAppeal, "Личный кабинет");
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