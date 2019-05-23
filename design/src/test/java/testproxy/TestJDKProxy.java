package testproxy;

import com.design.learn.proxy.CustomAspect;
import com.design.learn.proxy.JDKProxy;

public class TestJDKProxy {

    public static void main(String[] args) {

        //不使用代理
        User user = new User();
        user.setUserName("wwy");
        user.setPassword("q123456789");
        UserService userService = new UserServiceImpl();
        userService.saveUser(user);

        //使用代理
        UserService proxyUserService = (UserService) JDKProxy.createProxy(userService, new CustomAspect());
        user.setPassword("q123456789`1");
        user.setUserName("wwy1");
        proxyUserService.saveUser(user);
    }
}
