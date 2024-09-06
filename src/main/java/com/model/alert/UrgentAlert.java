package com.model.alert;

import java.time.Instant;
import java.util.List;

public class UrgentAlert extends Alert {
    public UrgentAlert(String title, String body) {
        super(title, body);
    }

    public UrgentAlert(String title, String body, Instant expirationTime) {
        super(title, body, expirationTime);
    }

    public UrgentAlert(UrgentAlert other) {
        super(other);
    }

    @Override
    public UrgentAlert copy() {
        return new UrgentAlert(this);
    }

    @Override
    public void appendAlert(List<Alert> alerts) {
        alerts.add(0, this);
    }
}
