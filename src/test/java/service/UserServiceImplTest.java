package service;

import org.junit.jupiter.api.Test;
import team.skylinekids.commonweal.factory.ServiceFactory2;
import team.skylinekids.commonweal.pojo.po.User;
import team.skylinekids.commonweal.service.UserService;

/**
 * @description: UserServiceImplTest
 * @date: 2021/12/28 15:46
 * @author: MysticalDream
 */
class UserServiceImplTest {

    UserService userService = ServiceFactory2.getServiceImplProxy(UserService.class);

    @Test
    void register() {

    }

    @Test
    void login() {
    }

    @Test
    void testLogin() {
    }

    @Test
    void getUserById() throws Exception {
        User userById = userService.getUserById(1);

    }

    @Test
    void getUserByUsername() {
    }

    @Test
    void isUserNameExisted() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void updateUserPassword() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void deleteUserById() {
    }
}