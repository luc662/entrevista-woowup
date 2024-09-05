package com.main.model.alert;

import java.time.Instant;

public class UrgentAlert extends Alert{
    public UrgentAlert(String title, String body) {
        super(title, body);
    }

    public UrgentAlert(String title, String body, Instant expirationTime) {
        super(title, body, expirationTime);
    }
}
