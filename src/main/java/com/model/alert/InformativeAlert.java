package com.model.alert;

import java.time.Instant;
import java.util.List;


public class InformativeAlert extends Alert {
    public InformativeAlert(String title, String body) {
        super(title, body);
    }

    public InformativeAlert(String title, String body, Instant expirationTime) {
        super(title, body, expirationTime);
    }

    public InformativeAlert(InformativeAlert other) {
        super(other);
    }

    @Override
    public InformativeAlert copy() {
        return new InformativeAlert(this);
    }

    @Override
    public void appendAlert(List<Alert> alerts) {
        alerts.add(this);
    }
}
