package com.main.model.user;


import com.exceptions.NonexistentUserException;
import com.exceptions.UserAlreadyRegistredException;
import com.model.User.UserRegister;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserRegisterTest {

    @Test
    public void addANewUserToRegisterTest() {

        UserRegister userRegister = new UserRegister();

        userRegister.registerUser("My user");
        userRegister.registerUser("My user 2");

        Assert.assertEquals(userRegister.getUserByName("My user").getName(), "My user");
        Assert.assertEquals(userRegister.getUserByName("My user 2").getName(), "My user 2");
    }

    @Test
    public void addAnAlreadyRegisteredUserThrowsExceptionTest() {
        assertThrows(UserAlreadyRegistredException.class, () -> {
                    UserRegister userRegister = new UserRegister();
                    userRegister.registerUser("My user");
                    userRegister.registerUser("My user");
                }
        );
    }

    @Test
    public void getANonRegisteredUserThrowsExceptionTest() {
        assertThrows(NonexistentUserException.class, () -> {
                    UserRegister userRegister = new UserRegister();
                    userRegister.getUserByName("My user");
                }
        );
    }
}
