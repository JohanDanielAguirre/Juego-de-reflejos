package com.example.demo4;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class Enemy extends Thread {
    private  GraphicsContext gc;

    private int x;
    private int y;
    private int size;

    private int timesleep;

    private int canvawiht;

    private int canvaheight;

    private  boolean live=true;
    private Rectangle shape;

    private EventHandler<CustomEvent> customEventHandler;


    private boolean isLive=true;
    Image enemyImage;

    public void setEnemyImage(Image enemyImage) {
        this.enemyImage = enemyImage;
    }

    public Enemy(int x, int y, int size, GraphicsContext gc, int canvawiht, int canvaheight, int timesleep, EventHandler<CustomEvent> customEventHandler) {
        File file= new File("image.png");
        String localUrl = String.valueOf(file.getAbsoluteFile());
        localUrl=localUrl.replace("image.png","");
        Image logo;
        logo = new Image(localUrl+"\\src\\main\\java\\com\\example\\demo4\\image.png");
        enemyImage=logo;
        this.x = x;
        this.y = y;
        this.size = size;
        this.gc = gc;
        this.canvawiht=canvawiht;
        this.canvaheight=canvaheight;
        this.shape=new Rectangle(x,y,size,size);
        this.timesleep=timesleep;
        this.customEventHandler = customEventHandler;
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                adjustTimeSleep();
            }
        }, 30000, 30000);
    }
    @Override
    public void run(){
        super.run();
        gc.drawImage(enemyImage, x, y, size, size);
        while (true){
            isLive=true;

                       try {
                sleep(timesleep);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(isLive){
                CustomEvent event = new CustomEvent();
                customEventHandler.handle(event);
            }
            gc.clearRect(x, y, size, size);
            shape.setX(x= (int) (Math.random()*(canvawiht-30)));
            shape.setY(y= (int) (Math.random()*(canvaheight-30)));
            gc.drawImage(enemyImage, x, y, size, size);
        }
    }
    public boolean clicked(int x, int y){
        Point2D d=new Point2D(x,y);
        if(shape.contains(d)){
            isLive=false;
            clear();
            return true;
        }
        return false;
    }
    public void clear(){
        gc.clearRect(x, y, size, size);
    }

    public void setlive(boolean b) {
        this.live=b;
    }
    public void adjustTimeSleep() {
        if (timesleep >= 100) {
            timesleep -= 100;
        }
    }
}

