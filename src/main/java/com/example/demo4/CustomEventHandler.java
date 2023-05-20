package com.example.demo4;


import javafx.event.EventHandler;

public class CustomEventHandler implements EventHandler<CustomEvent> {
    @Override
    public void handle(CustomEvent event) {
        // Lógica para manejar el evento personalizado
        System.out.println("Evento personalizado recibido");
        // ...
    }
}