package test.com.model.topic;

import com.main.exceptions.NonexistentUserError;
import com.main.exceptions.UserAlreadyRegistredException;
import com.main.model.User.User;
import com.main.model.alert.Alert;
import com.main.model.alert.InfromativeAlert;
import com.main.model.topic.Topic;
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
        Assert.assertTrue(topic.getAllAlerts().contains(alert));
    }

    @Test
    public void postAlertsAreAddedToRegisteredUsersListTest() {
        Topic topic = new Topic();
        Alert alert = new InfromativeAlert("alert title", "Alert body");
        User user1 = new User("Mi user");
        User user2 = new User("Mi user 2");
        topic.registerUser(user1);

        topic.receiveAlert(alert);
        Assert.assertTrue(user1.getAllAlerts().contains(alert));
        Assert.assertFalse(user2.getAllAlerts().contains(alert));
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
        Assert.assertTrue(user1.getAllAlerts().contains(alert));
        Assert.assertFalse(user2.getAllAlerts().contains(alert));
    }

    @Test
    public void postAlertToNonexistentUserTest() {
        assertThrows(NonexistentUserError.class, () -> {
                    Topic topic = new Topic();
                    Alert alert = new InfromativeAlert("alert title", "Alert body");
                    topic.receiveAlert(alert, "Mi user");
                }
        );
    }
}
