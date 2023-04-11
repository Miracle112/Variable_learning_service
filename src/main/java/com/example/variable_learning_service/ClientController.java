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


        back.setOnAction(event -> {
            open("/com/example/variable_learning_service/authorization.fxml", back, "Авторизация");
        });

        nameLbl.setText("Здравуствуйте Макисм Китанин");
        infoKindergarten.setOnAction(event -> {
            open("/com/example/variable_learning_service/infoKindergarten.fxml", infoKindergarten, "Информация о садике");
        });

        appealsBtn.setOnAction(event -> {
            open("/com/example/variable_learning_service/appeals.fxml", appealsBtn, "Написать обращение");
        });

        String requestAdmin = "SELECT * FROM variable_learning_service.appeals";
        try {
            fill(requestAdmin, approvedAppealsTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void fill(String querry, TableView<ObservableList> personalDataTable1) throws SQLException {
        personalDataTable1.getColumns().clear();
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        DBHandler dbHandler = new DBHandler();
        ResultSet resultSet = dbHandler.querry(querry);
        for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
            final int j = i;
            TableColumn col = new TableColumn(resultSet.getMetaData().getColumnLabel(i + 1));
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    if (param.getValue().get(j) == null) {
                        return new SimpleStringProperty("");
                    } else {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                }
            });
            personalDataTable1.getColumns().addAll(col);
        }
        while (resultSet.next()) {
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                row.add(resultSet.getString(i));
            }
            data.add(row);
        }
        personalDataTable1.setItems(data);
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
        stage.setResizable(false);
        stage.show();
    }
}