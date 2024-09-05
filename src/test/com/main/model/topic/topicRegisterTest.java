package com.main.model.topic;

import com.exceptions.TopicDoesNotExistException;
import com.model.User.User;
import com.model.alert.InfromativeAlert;
import com.model.topic.TopicRegister;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class topicRegisterTest {

    @Test
    public void createNewTopicRegistersWithUsers() {
        TopicRegister topicRegister = new TopicRegister();
        topicRegister.createTopic("My Topic");

        User user1 = new User("Mi user");
        User user2 = new User("Mi user 2");
        topicRegister.registerUserToTopic("My Topic", user1);
        topicRegister.registerUserToTopic("My Topic", user2);
        InfromativeAlert alert = new InfromativeAlert("alert 1", "alert 1");
        topicRegister.sendAlertToTopic("My Topic", alert);
        Assert.assertTrue(user1.getAllValidAlerts().contains(alert));
        Assert.assertTrue(user2.getAllValidAlerts().contains(alert));
    }

    @Test
    public void alertsAreSentOnlyToDestinationTopic() {
        TopicRegister topicRegister = new TopicRegister();
        topicRegister.createTopic("My Topic");
        topicRegister.createTopic("My Topic 2");
        User user1 = new User("Mi user");
        User user2 = new User("Mi user 2");
        topicRegister.registerUserToTopic("My Topic", user1);
        topicRegister.registerUserToTopic("My Topic 2", user2);
        InfromativeAlert alert = new InfromativeAlert("alert 1", "alert 1");
        topicRegister.sendAlertToTopic("My Topic", alert);
        Assert.assertTrue(user1.getAllValidAlerts().contains(alert));
        Assert.assertFalse(user2.getAllValidAlerts().contains(alert));
    }

    @Test
    public void sendAlertToUserInATopic() {
        TopicRegister topicRegister = new TopicRegister();
        topicRegister.createTopic("My Topic");

        User user1 = new User("Mi user");
        User user2 = new User("Mi user 2");
        topicRegister.registerUserToTopic("My Topic", user1);
        topicRegister.registerUserToTopic("My Topic", user2);
        InfromativeAlert alert = new InfromativeAlert("alert 1", "alert 1");
        topicRegister.sendAlertToUserInTopic("My Topic", alert, "Mi user");
        Assert.assertTrue(user1.getAllValidAlerts().contains(alert));
        Assert.assertFalse(user2.getAllValidAlerts().contains(alert));
    }

    @Test
    public void registerUserToNonCreatedTopicThrowsException() {
        assertThrows(TopicDoesNotExistException.class, () -> {
                    TopicRegister topicRegister = new TopicRegister();
                    User user1 = new User("Mi user");
                    topicRegister.registerUserToTopic("My Topic", user1);
                }
        );
    }

    @Test
    public void publicAnAlertToNonCreatedTopicThrowsException() {
        assertThrows(TopicDoesNotExistException.class, () -> {
                    TopicRegister topicRegister = new TopicRegister();
                    topicRegister.sendAlertToTopic("My Topic", new InfromativeAlert("alert 1", "alert 1"));
                }
        );
    }
}
