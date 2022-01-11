package com.example.snakeandladder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    Stage s1;
    @Override
    public void start(Stage stage) throws IOException {
        s1 = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("front-view.fxml"));
        Stage s = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 740, 640);
        s.setTitle("Snake and Ladder");
        s.setScene(scene);
        s1 = stage;
        s.show();
    }

    public static void main(String[] args) {
        launch();
    }
}