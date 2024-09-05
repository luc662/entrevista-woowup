package com.main.model.alert;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class InfromativeAlert extends Alert {
    public InfromativeAlert(String title, String body) {
        super(title, body);
    }

    public InfromativeAlert(String title, String body, Instant expirationTime) {
        super(title, body, expirationTime);
    }

    @Override
    public void apendMessage(ArrayList<Alert> alerts) {
        alerts.add(this);
    }
}
