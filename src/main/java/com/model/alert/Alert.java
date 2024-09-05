package com.model.alert;


import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;

public abstract class Alert {
    private String title;
    private String body;
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

    public abstract void apendAlert(ArrayList<Alert> alerts);

    public boolean isExpired(){
        return expirationTime
                .map(expiration -> expiration.isBefore(Instant.now()))
                .orElse(false);
    }

}
