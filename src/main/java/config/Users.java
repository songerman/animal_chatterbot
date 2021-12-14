package config;

import bot.MyDialog;
import java.util.HashMap;
import java.util.Map;

public class Users {
    public static Map<Long, MyDialog> users = new HashMap<>();

    public static void checkUser(User user) {
        if (!users.containsKey(user.Id)) {
            users.put(
                    user.Id,
                    new MyDialog(user.Id, user.Name));
        }
    }
    public static void deleteUser(User user) {
        users.remove(user.Id);
    }
}
