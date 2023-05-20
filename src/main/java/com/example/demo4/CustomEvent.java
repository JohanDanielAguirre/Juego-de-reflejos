package com.example.demo4;

import javafx.event.Event;
import javafx.event.EventType;

public class CustomEvent extends Event {
    public static final EventType<CustomEvent> CUSTOM_EVENT_TYPE = new EventType<>("CUSTOM_EVENT");

    public CustomEvent() {
        super(CUSTOM_EVENT_TYPE);
    }
}