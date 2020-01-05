package telegrambot;

import bot.Game;
import config.Users;
import org.telegram.telegrambots.meta.api.objects.Message;

public class TelegramDialog {

    public static String makeReply(Message msg) {
        if (msg.getText().equals("/exit")) {
            Users.deleteTgUser(msg);
            return "До свидания.";
        } else if (msg.getText().equals("/help")) {
            return Game.Rules;
        } else {
            String reply = Users.TelegramUsers.get(msg.getChatId()).getReaction(msg.getText());
            return reply;
        }
    }
}
