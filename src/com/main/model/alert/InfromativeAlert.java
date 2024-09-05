package com.main.model.alert;

import java.time.Instant;

public class InfromativeAlert extends Alert {
    public InfromativeAlert(String title, String body) {
        super(title, body);
    }

    public InfromativeAlert(String title, String body, Instant expirationTime) {
        super(title, body, expirationTime);
    }
}
