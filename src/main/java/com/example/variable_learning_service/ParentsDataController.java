package com.example.variable_learning_service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ParentsDataController {
    DBHandler dbHandler = DBHandler.getInstance();
    ResultSet gardenResultSet;
    Integer gardenId;
    int userId = AuthorizationController.id;

    @FXML
    private TextField address;

    @FXML
    private ComboBox<String> garden;

    @FXML
    private TextField name_child;

    @FXML
    private TextField patronymic_child;

    @FXML
    private Button sendBtn;

    @FXML
    private TextField short_name;

    @FXML
    private TextField short_name_child;

    @FXML
    private TextField surname_child;

    @FXML
    void initialize() {

        setBox("SELECT DISTINCT name FROM gardens", "name", garden);
        garden.setOnAction(event -> {
            gardenResultSet = dbHandler.querry("SELECT * FROM gardens WHERE name = '" + garden.getValue() + "'");
            try {
                gardenResultSet.next();
                gardenId = gardenResultSet.getInt(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        sendBtn.setOnAction(event -> {
            System.out.println(userId);
            List<? extends Serializable> listData = List.of(userId, address.getText(), gardenId, short_name.getText(), name_child.getText(),
                    surname_child.getText(), patronymic_child.getText(), short_name_child.getText());
            if(!dbHandler.checkFieldIsNull(listData)) {
                dbHandler.saveParentData(listData);
            } else {
                System.out.println("Пустые");
            }

            Main.open("/com/example/variable_learning_service/client.fxml", sendBtn, "Личный кабинет");
        });
    }


    public void setBox(String request, String nameColumn, ComboBox nameBox) {
        try {
            ObservableList<String> listacombo = FXCollections.observableArrayList();
            ResultSet resultSet = dbHandler.querry(request);
            while (resultSet.next()) {
                listacombo.add(resultSet.getString(nameColumn));
            }
            nameBox.setItems(listacombo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}