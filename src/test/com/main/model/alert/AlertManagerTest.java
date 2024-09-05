package com.main.model.alert;

import com.model.alertmanager.AlertManager;
import com.model.alert.InfromativeAlert;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;

public class AlertManagerTest {

    @Test
    public void SingleAlertTest() {
        AlertManager alertManager = new AlertManager();
        InfromativeAlert alert = new InfromativeAlert("alert 1", "alert 1");
        alertManager.storeAlert(alert);
        Assert.assertTrue(alertManager.getAllAlerts().contains(alert));
    }

    @Test
    public void SingleAlertWithFutureExpirationTest() {
        AlertManager alertManager = new AlertManager();
        Instant futureInstant = Instant.now().plus(Duration.ofDays(5));
        InfromativeAlert alert = new InfromativeAlert("alert 1", "alert 1", futureInstant);
        alertManager.storeAlert(alert);
        Assert.assertTrue(alertManager.getAllAlerts().contains(alert));
        Assert.assertTrue(alertManager.getAllNonExpiredAlerts().contains(alert));
    }


    @Test
    public void SingleAlertWithPastExpirationTest() {
        AlertManager alertManager = new AlertManager();
        Instant pastInstant = Instant.now().minus(Duration.ofDays(5));
        InfromativeAlert alert = new InfromativeAlert("alert 1", "alert 1", pastInstant);
        alertManager.storeAlert(alert);
        Assert.assertTrue(alertManager.getAllAlerts().contains(alert));
        Assert.assertFalse(alertManager.getAllNonExpiredAlerts().contains(alert));
    }

    @Test
    public void TwoAlertsWithAReadAlertTest() {
        AlertManager alertManager = new AlertManager();
        InfromativeAlert alert = new InfromativeAlert("alert 1", "alert 1");
        InfromativeAlert readAlert = new InfromativeAlert("alert 2", "alert 2");
        alertManager.storeAlert(alert);
        alertManager.storeAlert(readAlert);
        readAlert.markAsRead();
        Assert.assertTrue(alertManager.getAllNonReadAndNonExpiredAlerts().contains(alert));
        Assert.assertFalse(alertManager.getAllNonReadAndNonExpiredAlerts().contains(readAlert));
    }
}
