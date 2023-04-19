package com.example.variable_learning_service;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;


public class AuthorizationController {
    static int id;

    @FXML
    private TextField email;

    @FXML
    private Button login;

    @FXML
    private PasswordField password;

    @FXML
    private Button registration;

    @FXML
    void initialize() {
        DBHandler dbHandler = DBHandler.getInstance();

        login.setOnAction(actionEvent -> {
            String emailText = email.getText().trim();
            String passwordText = password.getText().trim();
            try {
                if (!emailText.equals("") && !passwordText.equals("")) {
                    Pair<Integer, String> user = dbHandler.getRole(emailText, passwordText);
                    String str = user.getValue();
                    id = user.getKey();
                    if (str.equals("1")) {
                        Main.open("/com/example/variable_learning_service/admin.fxml", login, "Администратор");
                    } else if (str.equals("2")) {
                        Main.open("/com/example/variable_learning_service/client.fxml", login, "Заведующий");
                    } else if (str.equals("3")) {
                        Main.open("/com/example/variable_learning_service/client.fxml", login, "Специалист");
                    } else if (str.equals("4")) {
                        if (!dbHandler.checkParentDataIsNotNull(id)) {
                            Main.open("/com/example/variable_learning_service/parents_data.fxml", login, "Дополнительные данные");
                        } else {
                            Main.open("/com/example/variable_learning_service/client.fxml", login, "Личный кабинет");
                        }
                    }
                }
            else if(!emailText.equals("")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Предупреждение");
                alert.setContentText("Пустой пароль");
                alert.showAndWait();
            }
            else if(!passwordText.equals("")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Предупреждение");
                alert.setContentText("Пустой логин");
                alert.showAndWait();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Предупреждение");
                alert.setContentText("Неправильный логин или пароль");
                alert.showAndWait();
            }
        } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Предупреждение");
                alert.setContentText("Неправильный логин или пароль");
                alert.showAndWait();
            }});
        registration.setOnAction(actionEvent -> Main.open("/com/example/variable_learning_service/registration.fxml",
                registration, "Регистрация"));
    }
}
