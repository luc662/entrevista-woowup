package com.model.alert;


import com.model.alert.readed.AlertReadState;
import com.model.alert.readed.AlertUnreadReadState;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;

public abstract class Alert {
    private String title;
    private String body;
    private AlertReadState alertReadState;
    private Optional<Instant> expirationTime;

    protected Alert(String title, String body) {
        this.title = title;
        this.body = body;
        this.expirationTime = Optional.empty();
        this.alertReadState = new AlertUnreadReadState();
    }

    protected Alert(String title, String body, Instant expirationTime) {
        this.title = title;
        this.body = body;
        this.expirationTime = Optional.ofNullable(expirationTime);
        this.alertReadState = new AlertUnreadReadState();
    }

    public abstract void appendAlert(ArrayList<Alert> alerts);

    public boolean isExpired() {
        return this.expirationTime
                .map(expiration -> expiration.isBefore(Instant.now()))
                .orElse(false);
    }

    public void markAsRead() {
        this.alertReadState = alertReadState.markAsRead();
    }

    public boolean isRead() {
        return this.alertReadState.isRead();
    }
}
