package com.example.variable_learning_service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AppealsContrller {
    DBHandler dbHandler = DBHandler.getInstance();
    String FIOChildGarden;

    @FXML
    private TextField FIO_child;

    @FXML
    private TextArea comment;

    @FXML
    private TextField date;

    @FXML
    private ComboBox<String> kind_of_help;

    @FXML
    private Button sendAppeal;

    @FXML
    private TextField time;


    @FXML
    void initialize() {
        int userId = AuthorizationController.id;
        ResultSet resultSetChild = dbHandler.querry("SELECT short_name_child FROM parents_data WHERE id_user = '" + userId + "'");
        try {
            resultSetChild.next();
            FIOChildGarden = resultSetChild.getString("short_name_child");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        FIO_child.setText(FIOChildGarden);

        ObservableList<String> kindOfHelpList = FXCollections.observableArrayList("Психологическая", "Логопедическая",
                "Консультационная", "Медецинская", "Педогогическая", "Методическая", "Диагностическая", "Лекотека");
        kind_of_help.setItems(kindOfHelpList);


        sendAppeal.setOnAction(event -> {
            dbHandler.sendAppeals(userId, kind_of_help.getValue(), date.getText(), time.getText(), comment.getText());
            Main.open("/com/example/variable_learning_service/client.fxml", sendAppeal, "Личный кабинет");
        });




    }
}