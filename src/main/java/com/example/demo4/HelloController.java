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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();
        setCanvasEvents();
        paint();
    }

    private void setCanvasEvents() {
        canvas.setFocusTraversable(true);
        canvas.setOnMouseClicked(keyvent->{
            if(enemy.clicked((int)keyvent.getX(),(int)keyvent.getY())){
                score=score+1;
                l2.setText("Score:" + score);
            }else{
                lifes=lifes-1;
                l1.setText("lifes:" + lifes);
                if(lifes<=0){
                    System.exit(9000);
                }
            }
            enemy.setlive(false);
        });

    }

    private void paint() {
            int x= (int) (Math.random()*canvas.getHeight());
            int y= (int) (Math.random()*canvas.getWidth());
            enemy = new Enemy(x,y, 30,gc,(int)canvas.getWidth(),(int)canvas.getHeight(),1000,this);
            enemy.setDaemon(true);
            enemy.start();
    }
    @Override
    public void handle(CustomEvent event) {
         lifes--;
         if(lifes<=0){
             System.exit(420);
         }
        Platform.runLater(() -> {l1.setText("lifes:" + lifes);});

    }
}