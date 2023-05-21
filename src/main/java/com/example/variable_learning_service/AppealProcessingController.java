package com.example.variable_learning_service;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AppealProcessingController {

    @FXML
    private Button appointBtn;

    @FXML
    private Button backBtn;

    @FXML
    private TextField comment;

    @FXML
    private TextField date;

    @FXML
    private TextField kind_of_help;

    @FXML
    private ComboBox<String> specialist;

    @FXML
    private TextField time;

    @FXML
    private TextField user;

    @FXML
    private Text textNum;

    @FXML
    void initialize() {


    }

}

//    String s = "1122 - sdf sd1f";
//    String f = s.substring(0, s.indexOf(" "));
//    System.out.println(f);
