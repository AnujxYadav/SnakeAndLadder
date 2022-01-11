package com.example.snakeandladder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class FrontController {
    private Stage stage;
    @FXML
    private Button start_button;
    @FXML
    private Label winner_name;

    public void start_game(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FrontController.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 740, 640);
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setTitle("Start-Game");
        stage.setScene(scene);
    }

    public void end_game(String name) throws IOException {


    }

    public void exit(ActionEvent e)
    {
        System.exit(0);
    }


}
