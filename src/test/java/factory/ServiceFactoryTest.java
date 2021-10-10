package factory;

import org.junit.jupiter.api.Test;
import team.skylinekids.commonweal.factory.ServiceFactory;
import team.skylinekids.commonweal.pojo.po.User;
import team.skylinekids.commonweal.service.UserService;

import static org.junit.jupiter.api.Assertions.*;

class ServiceFactoryTest {

    @Test
    void getUserService() {

        UserService userService = ServiceFactory.getUserService();
        userService.register(new User());

    }
}