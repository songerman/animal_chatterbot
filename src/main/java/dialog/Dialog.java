package dialog;

import bot.Game;
import config.User;
import config.Users;

public class Dialog {
    public static String makeReply(String msg, User user) {
        if (msg.equals("/exit")) {
            Users.deleteUser(user);
            return "До свидания.";
        } else if (msg.equals("/help")) {
            return Game.Rules;
        }else if (msg.equals("Начать")) {
            return Users.users.get(user.Id).getReaction("/start");
        } else {
            return Users.users.get(user.Id).getReaction(msg);
        }
    }
}
