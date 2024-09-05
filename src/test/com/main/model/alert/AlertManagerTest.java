package com.main.model.alert;

import com.model.alertmanager.AlertManager;
import com.model.alert.InformativeAlert;
import com.model.alertmanager.TopicAlertManager;
import com.model.alertmanager.UserAlertManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;

public class AlertManagerTest {

    @Test
    public void SingleAlertTest() {
        AlertManager alertManager = new UserAlertManager();
        InformativeAlert alert = new InformativeAlert("alert 1", "alert 1");
        alertManager.storeAlert(alert);
        Assert.assertTrue(alertManager.getAllAlerts().contains(alert));
    }

    @Test
    public void SingleAlertWithFutureExpirationTest() {
        AlertManager alertManager = new TopicAlertManager();
        Instant futureInstant = Instant.now().plus(Duration.ofDays(5));
        InformativeAlert alert = new InformativeAlert("alert 1", "alert 1", futureInstant);
        alertManager.storeAlert(alert);
        Assert.assertTrue(alertManager.getAllAlerts().contains(alert));
        Assert.assertTrue(alertManager.getAllValidAlerts().contains(alert));
    }


    @Test
    public void SingleAlertWithPastExpirationTest() {
        AlertManager alertManager = new TopicAlertManager();
        Instant pastInstant = Instant.now().minus(Duration.ofDays(5));
        InformativeAlert alert = new InformativeAlert("alert 1", "alert 1", pastInstant);
        alertManager.storeAlert(alert);
        Assert.assertTrue(alertManager.getAllAlerts().contains(alert));
        Assert.assertFalse(alertManager.getAllValidAlerts().contains(alert));
    }

    @Test
    public void TwoAlertsWithAReadAlertTest() {
        AlertManager alertManager = new UserAlertManager();
        InformativeAlert alert = new InformativeAlert("alert 1", "alert 1");
        InformativeAlert readAlert = new InformativeAlert("alert 2", "alert 2");
        alertManager.storeAlert(alert);
        alertManager.storeAlert(readAlert);
        readAlert.markAsRead();
        Assert.assertTrue(alertManager.getAllValidAlerts().contains(alert));
        Assert.assertFalse(alertManager.getAllValidAlerts().contains(readAlert));
    }
}
