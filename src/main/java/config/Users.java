package config;

import bot.MyDialog;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.Map;

public class Users {
    public static Map<Long, MyDialog> TelegramUsers = new HashMap<>();
    //public static Map<Long, MyDialog> Users = new HashMap<>();

    public static void checkTgUser(Message msg) {
        if (!TelegramUsers.containsKey(msg.getChatId())) {
            TelegramUsers.put(msg.getChatId(), new MyDialog(msg.getChatId(), msg.getFrom().getFirstName()));
        }
    }

    public static void deleteTgUser(Message msg) {
        TelegramUsers.remove(msg.getChatId());
    }
}
