package com.example.variable_learning_service;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class RegistrationController {

    @FXML
    private Button back;

    @FXML
    private TextField login;

    @FXML
    private TextField name;

    @FXML
    private TextField password;

    @FXML
    private TextField patronymic;

    @FXML
    private Button reg_button;

    @FXML
    private TextField surname;

    @FXML
    void initialize() {
        DBHandler dbHandler = new DBHandler();

        back.setOnAction(event -> {
            open("/com/example/variable_learning_service/authorization.fxml", back, "Авторизация");
        });

        reg_button.setOnAction(event -> {
            if (!login.getText().equals("") && !password.getText().equals("") && !surname.getText().equals("") &&
                    !name.getText().equals("") && !patronymic.getText().equals("")) {
                int id_role = 4;
                dbHandler.registration(name.getText(), surname.getText(), patronymic.getText(), id_role, login.getText(), password.getText());
                open("/com/example/variable_learning_service/authorization.fxml", reg_button, "Авторизация");
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Предупреждение");
                alert.setContentText("Заполните все поля");
                alert.showAndWait();
            }
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
