package com.main.model.user;

import com.model.User.User;
import com.model.alert.Alert;
import com.model.alert.InformativeAlert;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class UserTest {

    @Test
    public void addValidAlertToUser() {
        User user = new User("My user");
        Alert alert = new InformativeAlert("alert 1", "Alert 1");
        user.receiveAlert(alert);
        Assert.assertTrue(user.getAllValidAlerts().contains(alert));
    }

    @Test
    public void addAlertWithFutureExpirationToUser() {
        User user = new User("My user");
        Instant futureInstant = Instant.now().plus(Duration.ofDays(5));
        InformativeAlert alert = new InformativeAlert("alert 1", "alert 1", futureInstant);
        user.receiveAlert(alert);
        Assert.assertTrue(user.getAllValidAlerts().contains(alert));
    }

    @Test
    public void addAlertWithPastExpirationToUser() {
        User user = new User("My user");
        Instant pastInstant = Instant.now().minus(Duration.ofDays(5));
        InformativeAlert alert = new InformativeAlert("alert 1", "alert 1", pastInstant);
        user.receiveAlert(alert);
        Assert.assertFalse(user.getAllValidAlerts().contains(alert));
    }

    @Test
    public void addReadAlertToUser() {
        User user = new User("My user");
        InformativeAlert alert = new InformativeAlert("alert 1", "alert 1");
        user.receiveAlert(alert);
        Assert.assertTrue(user.getAllValidAlerts().contains(alert));
        alert.markAsRead();
        Assert.assertFalse(user.getAllValidAlerts().contains(alert));
    }

    @Test
    public void addMultipleAlertsToUser() {
        User user = new User("My user");
        InformativeAlert alert1 = new InformativeAlert("alert 1", "alert 1");
        InformativeAlert alert2 = new InformativeAlert("alert 2", "alert 2");
        Instant futureInstant = Instant.now().plus(Duration.ofDays(5));
        Instant pastInstant = Instant.now().minus(Duration.ofDays(5));
        InformativeAlert alert3 = new InformativeAlert("alert 3", "alert 3", pastInstant);
        InformativeAlert alert4 = new InformativeAlert("alert 4", "alert 4", futureInstant);

        user.receiveAlert(alert1);
        user.receiveAlert(alert2);
        user.receiveAlert(alert3);
        user.receiveAlert(alert4);

        alert2.markAsRead();
        List validAlerts = user.getAllValidAlerts();
        Assert.assertTrue(validAlerts.contains(alert1));
        Assert.assertFalse(validAlerts.contains(alert2));
        Assert.assertFalse(validAlerts.contains(alert3));
        Assert.assertTrue(validAlerts.contains(alert4));
    }

}
