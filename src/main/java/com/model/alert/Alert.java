package com.model.alert;


import com.model.alert.readed.AlertReadState;
import com.model.alert.readed.AlertUnreadReadState;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public abstract class Alert {
    private String title;
    private String body;
    private AlertReadState alertReadState;
    private Optional<Instant> expirationTime;
    private boolean isForSpecificUser;

    protected Alert(String title, String body) {
        this.title = title;
        this.body = body;
        this.expirationTime = Optional.empty();
        this.alertReadState = new AlertUnreadReadState();
        this.isForSpecificUser = false;
    }

    protected Alert(Alert other) {
        this.title = other.title;
        this.body = other.body;
        this.expirationTime = other.expirationTime;
        this.alertReadState = other.alertReadState;
        this.isForSpecificUser = other.isForSpecificUser;
    }

    protected Alert(String title, String body, Instant expirationTime) {
        this.title = title;
        this.body = body;
        this.expirationTime = Optional.ofNullable(expirationTime);
        this.alertReadState = new AlertUnreadReadState();
        this.isForSpecificUser = false;
    }

    public boolean isForSpecificUser() {
        return isForSpecificUser;
    }

    public abstract void appendAlert(List<Alert> alerts);

    public abstract Alert copy();

    public void markForSpecificUser() {
        isForSpecificUser = true;
    }

    public boolean isExpired() {
        return this.expirationTime.isPresent() && this.expirationTime.get().isBefore(Instant.now());
    }

    public void markAsRead() {
        this.alertReadState = alertReadState.markAsRead();
    }

    public boolean isRead() {
        return this.alertReadState.isRead();
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}

