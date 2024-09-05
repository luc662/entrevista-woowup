package com.main.model.topic;

import com.exceptions.TopicDoesNotExistException;
import com.model.User.User;
import com.model.alert.Alert;
import com.model.alert.InformativeAlert;
import com.model.topic.Topic;
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
        InformativeAlert alert = new InformativeAlert("alert 1", "alert 1");
        topicRegister.sendAlertToTopic("My Topic", alert);
        Alert userAlertCopy1= user1.getAllValidAlerts().get(0);
        Alert userAlertCopy2= user2.getAllValidAlerts().get(0);

        Assert.assertEquals(userAlertCopy1.getTitle(), alert.getTitle());
        Assert.assertEquals(userAlertCopy1.getBody(), alert.getBody());
        Assert.assertEquals(userAlertCopy2.getTitle(), alert.getTitle());
        Assert.assertEquals(userAlertCopy2.getBody(), alert.getBody());
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
        InformativeAlert alert = new InformativeAlert("alert 1", "alert 1");
        topicRegister.sendAlertToTopic("My Topic", alert);
        Alert userAlertCopy= user1.getAllValidAlerts().get(0);

        Assert.assertEquals(userAlertCopy.getTitle(), alert.getTitle());
        Assert.assertEquals(userAlertCopy.getBody(), alert.getBody());
        Assert.assertEquals(user2.getAllValidAlerts().size(), 0);
    }

    @Test
    public void sendAlertToUserInATopic() {
        TopicRegister topicRegister = new TopicRegister();
        topicRegister.createTopic("My Topic");

        User user1 = new User("Mi user");
        User user2 = new User("Mi user 2");
        topicRegister.registerUserToTopic("My Topic", user1);
        topicRegister.registerUserToTopic("My Topic", user2);
        InformativeAlert alert = new InformativeAlert("alert 1", "alert 1");
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
                    topicRegister.sendAlertToTopic("My Topic", new InformativeAlert("alert 1", "alert 1"));
                }
        );
    }
}
