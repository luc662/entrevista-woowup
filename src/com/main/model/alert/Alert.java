package com.main.model.alert;


import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;

public abstract class Alert {
    protected String title;
    protected String body;
    protected Optional<Instant> expirationTime;

    protected Alert(String title, String body) {
        this.title = title;
        this.body = body;
        this.expirationTime = Optional.empty();
    }

    protected Alert(String title, String body, Instant expirationTime) {
        this.title = title;
        this.body = body;
        this.expirationTime = Optional.ofNullable(expirationTime);
    }

    public abstract void apendMessage(ArrayList<Alert> alerts);

}
