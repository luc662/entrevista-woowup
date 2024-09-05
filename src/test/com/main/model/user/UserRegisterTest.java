package com.main.model.user;


import com.exceptions.UserAlreadyRegistredException;
import com.model.User.User;
import com.model.User.UserRegister;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserRegisterTest {

    @Test
    public void addANewUserToRegisterTest() {
        UserRegister userRegister = new UserRegister();
        User user1 = new User("Mi user");
        User user2 = new User("Mi user 2");
        userRegister.registerUser(user1);
        userRegister.registerUser(user2);
        Collection<User> allUsers = userRegister.getAllUsers();
        Assert.assertEquals(allUsers.size(), 2);
        Assert.assertTrue(allUsers.contains(user1));
        Assert.assertTrue(allUsers.contains(user2));
    }

    @Test
    public void addAnAlreadyRegisteredUserThrowsExceptionTest() {
        assertThrows(UserAlreadyRegistredException.class, () -> {
                    UserRegister userRegister = new UserRegister();
                    User user1 = new User("Mi user");
                    User user2 = new User("Mi user");
                    userRegister.registerUser(user1);
                    userRegister.registerUser(user2);
                }
        );
    }
}
