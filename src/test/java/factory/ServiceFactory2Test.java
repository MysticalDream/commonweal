package factory;

import org.junit.jupiter.api.Test;
import team.skylinekids.commonweal.factory.ServiceFactory2;
import team.skylinekids.commonweal.pojo.po.User;
import team.skylinekids.commonweal.service.UserService;

class ServiceFactory2Test {

    @Test
    void getServiceProxy() throws Exception {
        UserService userService = ServiceFactory2.getServiceImplProxy(UserService.class);
        User user = userService.getUserById(1);
        System.out.println(user);
    }
}