package com.example.demo4;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.event.EventType;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable,EventHandler<CustomEvent> {
    @FXML
    Canvas canvas;
    @FXML
    Label l1;
    @FXML
    Label l2;


    private GraphicsContext gc;

    private Enemy enemy;


    private int score;

    private int lifes=3;
    private ArrayList<Enemy> enemies = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();
        setCanvasEvents();
        paint();
    }

    private void setCanvasEvents() {
        canvas.setFocusTraversable(true);
        canvas.setOnMouseClicked(keyvent -> {
            boolean enemyClicked = false; // Variable para indicar si se hizo clic en algún enemigo
            for (Enemy enemy : enemies) {
                if (enemy.clicked((int) keyvent.getX(), (int) keyvent.getY())) {
                    score = score + 1;
                    l2.setText("Score:" + score);
                    enemy.setlive(false);
                    enemyClicked = true; // Actualizar la variable indicando que se hizo clic en un enemigo
                    break; // Salir del bucle una vez que se haya encontrado y procesado el enemigo correspondiente
                }
            }
            if (!enemyClicked) {
                lifes = lifes - 1;
                l1.setText("lifes:" + lifes);
                if (lifes <= 0) {
                    System.exit(9000); //el poder de las botellas fue de mas de 9000 :'(
                }
            }
        });
    }

    private void paint() {
        int enemyCount = 2;
        for (int i = 0; i < enemyCount; i++) {
            int x = (int) (Math.random() * (600-30));
            int y = (int) (Math.random() * (400-30));
            Enemy enemy = new Enemy(x, y, 30, gc, (int) canvas.getWidth(), (int) canvas.getHeight(), 5000, this);
            enemy.setDaemon(true);
            enemies.add(enemy);
            enemy.start();
        }
    }
    @Override
    public void handle(CustomEvent event) {
         lifes--;
         if(lifes<=0){
             System.exit(420); // cumbia 420
         }
        Platform.runLater(() -> {l1.setText("lifes:" + lifes);});

    }
}