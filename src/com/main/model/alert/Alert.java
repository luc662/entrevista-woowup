package com.main.model.alert;


import java.time.Instant;
import java.util.Optional;

public class Alert {
    protected String title;
    protected String body;
    protected Optional<Instant> expirationTime;

    public Alert(String title, String body) {
        this.title = title;
        this.body = body;
        this.expirationTime = Optional.empty();
    }

    public Alert(String title, String body, Instant expirationTime) {
        this.title = title;
        this.body = body;
        this.expirationTime = Optional.ofNullable(expirationTime);
    }


}
