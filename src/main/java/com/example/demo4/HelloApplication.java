package com.example.demo4;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class HelloApplication extends Application {
    private final int anchopantalla = 640;
    private final int largopantalla = 480;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), anchopantalla, largopantalla);
        stage.setTitle("Mousegame");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest((WindowEvent event) -> {
            Platform.exit(); // Detener la aplicación de JavaFX
            System.exit(0); // Detener la aplicación por completo
        });
    }

    public static void main(String[] args) {
        launch();
    }
}