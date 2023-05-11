package com.example.variable_learning_service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddUsersController {

    @FXML
    private Button addBtn;

    @FXML
    private Button backBtn;

    @FXML
    private ComboBox<String> id_role;

    @FXML
    private TextField login;

    @FXML
    private TextField name;

    @FXML
    private TextField password;

    @FXML
    private TextField patronymic;

    @FXML
    private TextField surname;

    @FXML
    void initialize() {
        DBHandler dbHandler = DBHandler.getInstance();

        ObservableList<String> kindOfHelpList = FXCollections.observableArrayList("Администратор", "Заведующий", "Специалист");
        id_role.setItems(kindOfHelpList);

        backBtn.setOnAction(event -> {
            Main.open("/com/example/variable_learning_service/admin.fxml", backBtn, "Администратор");
        });

        addBtn.setOnAction(event -> {
            dbHandler.registration(name.getText(), surname.getText(), patronymic.getText(), getRole(id_role.getValue()), login.getText(), password.getText());
            Main.open("/com/example/variable_learning_service/admin.fxml", addBtn, "Администратор");
        });
    }

    public int getRole(String role) {
        for (int i=0; i<3; i++) {
            switch (role){
                case "Администратор":
                    return 1;
                case "Заведующий":
                    return 2;
                case "Специалист":
                    return 3;
            }
        }
        return 0;
    }

}