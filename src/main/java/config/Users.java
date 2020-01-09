package config;

import bot.MyDialog;
import java.util.HashMap;
import java.util.Map;

public class Users {
    public static Map<Long, MyDialog> Users = new HashMap<>();

    public static void checkUser(User user) {
        if (!Users.containsKey(user.Id)) {
            Users.put(
                    user.Id,
                    new MyDialog(new Long(user.Id), user.Name));
        }
    }
    public static void deleteUser(User user) {
        Users.remove(user.Id);
    }
}
