package com.duncangb.islands.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        if (!(new File(islandController.GAME_INFO_DIRECTORY).isDirectory())) {
            new File(islandController.CHUNK_DIRECTORY).mkdirs();
        } else {
            new File(islandController.GAME_INFO_DIRECTORY).delete();
        }

        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        primaryStage.setTitle("Islands");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
