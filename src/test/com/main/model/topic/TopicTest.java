package com.main.model.topic;


import com.exceptions.NonexistentUserException;
import com.exceptions.UserAlreadyRegistredException;
import com.model.User.User;
import com.model.alert.Alert;
import com.model.alert.InfromativeAlert;
import com.model.topic.Topic;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TopicTest {

    @Test
    public void addANewUserToRegisterTest() {
        Topic topic = new Topic();
        User user1 = new User("Mi user");
        User user2 = new User("Mi user 2");
        topic.registerUser(user1);
        topic.registerUser(user2);
        Collection<User> allUsers = topic.getAllUsers();
        Assert.assertEquals(allUsers.size(), 2);
        Assert.assertTrue(allUsers.contains(user1));
        Assert.assertTrue(allUsers.contains(user2));
    }

    @Test
    public void addAnAlreadyRegistredUserThrowsExceptionTest() {
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
        Alert alert = new InfromativeAlert("alert title", "Alert body");
        topic.receiveAlert(alert);
        Assert.assertTrue(topic.getAllNonExpiredAlerts().contains(alert));
    }

    @Test
    public void postAlertsAreAddedToRegisteredUsersListTest() {
        Topic topic = new Topic();
        Alert alert = new InfromativeAlert("alert title", "Alert body");
        User user1 = new User("Mi user");
        User user2 = new User("Mi user 2");
        topic.registerUser(user1);

        topic.receiveAlert(alert);
        Assert.assertTrue(user1.getAllValidAlerts().contains(alert));
        Assert.assertFalse(user2.getAllValidAlerts().contains(alert));
    }

    @Test
    public void postAlertToSpecificUserInTopicTest() {
        Topic topic = new Topic();
        Alert alert = new InfromativeAlert("alert title", "Alert body");
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
                    Alert alert = new InfromativeAlert("alert title", "Alert body");
                    topic.receiveAlert(alert, "Mi user");
                }
        );
    }
}
