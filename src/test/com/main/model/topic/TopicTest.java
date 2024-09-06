package com.main.model.topic;


import com.exceptions.NonexistentUserException;
import com.exceptions.UserAlreadyRegistredException;
import com.model.User.User;
import com.model.alert.Alert;
import com.model.alert.InformativeAlert;
import com.model.topic.Topic;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TopicTest {

    @Test
    public void addAnAlreadyRegisteredUserThrowsExceptionTest() {
        assertThrows(UserAlreadyRegistredException.class, () -> {
                    Topic topic = new Topic();
                    User user1 = new User("Mi user");
                    User user2 = new User("Mi user");
                    topic.registerUser(user1);
                    topic.registerUser(user2);
                }
        );
    }

    @Test
    public void postAlertsAreAddedToTopicListTest() {
        Topic topic = new Topic();
        Alert alert = new InformativeAlert("alert title", "Alert body");
        topic.receiveAlert(alert);
        Assert.assertTrue(topic.getAllNonExpiredAlerts().contains(alert));
    }

    @Test
    public void postAlertsAreAddedToRegisteredUsersListTest() {
        Topic topic = new Topic();
        Alert alert = new InformativeAlert("alert title", "Alert body");
        User user1 = new User("Mi user");
        User user2 = new User("Mi user 2");
        topic.registerUser(user1);

        topic.receiveAlert(alert);
        Alert userAlert = user1.getAllValidAlerts().get(0);
        Assert.assertEquals(userAlert.getTitle(), alert.getTitle());
        Assert.assertEquals(userAlert.getBody(), alert.getBody());
        Assert.assertTrue(user2.getAllValidAlerts().isEmpty());
    }

    @Test
    public void postAlertToSpecificUserInTopicTest() {
        Topic topic = new Topic();
        Alert alert = new InformativeAlert("alert title", "Alert body");
        User user1 = new User("Mi user");
        User user2 = new User("Mi user 2");
        topic.registerUser(user1);
        topic.registerUser(user2);
        topic.receiveAlert(alert, "Mi user");
        Assert.assertTrue(user1.getAllValidAlerts().contains(alert));
        Assert.assertFalse(user2.getAllValidAlerts().contains(alert));
    }

    @Test
    public void postAlertToNonexistentUserTest() {
        assertThrows(NonexistentUserException.class, () -> {
                    Topic topic = new Topic();
                    Alert alert = new InformativeAlert("alert title", "Alert body");
                    topic.receiveAlert(alert, "Mi user");
                }
        );
    }

    @Test
    public void markAsReadAMessageSentToATopicTest() {
        Topic topic = new Topic();
        User user1 = new User("Mi user");
        User user2 = new User("Mi user 2");
        topic.registerUser(user1);
        topic.registerUser(user2);
        Alert alert = new InformativeAlert("alert title", "Alert body");
        topic.receiveAlert(alert);
        Alert user1AlertCopy = user1.getAllValidAlerts().get(0);
        Alert user2AlertCopy = user2.getAllValidAlerts().get(0);

        user1AlertCopy.markAsRead();

        Assert.assertTrue(user1AlertCopy.isRead());
        Assert.assertFalse(user2AlertCopy.isRead());
    }


    @Test
    public void AlertsToSpecificUsersAreMarkedAsSuchTest() {
        Topic topic = new Topic();
        Alert alert1 = new InformativeAlert("alert title", "Alert body");
        Alert alert2 = new InformativeAlert("alert title", "Alert body");

        User user1 = new User("Mi user");

        topic.registerUser(user1);
        topic.receiveAlert(alert1, "Mi user");
        topic.receiveAlert(alert2);

        Alert alert1Copy = user1.getAllValidAlerts().get(0);
        Alert alert2Copy = user1.getAllValidAlerts().get(1);

        Assert.assertTrue(alert1Copy.isForSpecificUser());
        Assert.assertFalse(alert2Copy.isForSpecificUser());

    }
}
