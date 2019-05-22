package testproxy;

import java.text.MessageFormat;

public class UserServiceImpl implements UserService {
    @Override
    public boolean saveUser(User user) {
        System.out.println("保存用户");
        System.out.println(MessageFormat.format("user={0}",user.toString()));

        return true;
    }

    @Override
    public Boolean updateUser(User user) {
        System.out.println("更新用户");
        System.out.println(MessageFormat.format("user={0}",user.toString()));

        return true;
    }
}
