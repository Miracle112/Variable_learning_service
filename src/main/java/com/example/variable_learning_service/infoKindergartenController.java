package com.example.variable_learning_service;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class infoKindergartenController {
    int userId = AuthorizationController.id;
    String nameGarden;
    String addressGarden;
    String timeGarden;
    String FIOChildGarden;

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

        DBHandler dbHandler = DBHandler.getInstance();
        ResultSet resultSetGarden = dbHandler.querry("SELECT * FROM gardens WHERE id_gardens = (SELECT id_garden FROM " +
                "parents_data WHERE id_user = '" + userId + "')");
        ResultSet resultSetChild = dbHandler.querry("SELECT short_name_child FROM parents_data WHERE id_user = '" + userId + "'");
        try {
            resultSetGarden.next();
            resultSetChild.next();
            nameGarden = resultSetGarden.getString("name");
            addressGarden = resultSetGarden.getString("address");
            timeGarden = resultSetGarden.getString("working_hours");
            FIOChildGarden = resultSetChild.getString("short_name_child");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        nameKindergarten.setText("Детский сад " + nameGarden);
        fieldAddress.setText(addressGarden);
        fieldTime.setText(timeGarden);
        fieldFullName.setText(FIOChildGarden);

        back.setOnAction(event -> {
            Main.open("/com/example/variable_learning_service/client.fxml", back, "Личный кабинет");
        });
    }

}