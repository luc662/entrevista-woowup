package test.com.model.user;


import com.main.model.User.User;
import com.main.model.User.UserRegister;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collection;

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

}
