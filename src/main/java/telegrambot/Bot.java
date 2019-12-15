package telegrambot;

import bot.MyDialog;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;



public class Bot extends TelegramLongPollingBot {
    public HashMap<Long, MyDialog> users = new HashMap<>();
    //MyDialog dialog = new MyDialog();
    public static String Rules =
            "Для начала игры введите /start.\n" +
                    "Чтобы узнать правила, введите /help.\n" +
                    "Чтобы узнать счёт, введите /score.\n" +
                    "Чтобы начать сначала, введите /again.\n" +
                    "Чтобы выйти из игры, введите /exit\n";
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        //message.getFrom().getFirstName();
        if (!users.containsKey(message.getChatId())){
            users.put(message.getChatId(), new MyDialog(message.getChatId(), message.getFrom().getFirstName()));
        }
        if (message != null && message.hasText()) {
            if (message.getText().equals("/exit")){
                users.remove(message.getChatId());
                sendMsg(message, "До свидания.");
            } else if (message.getText().equals("/help")) {
                sendMsg(message, Rules);
            } else {
                String reply = users.get(message.getChatId()).getReaction(message.getText());
                sendMsg(message, reply);
            }
        }
    }

    public synchronized void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "guess_animal_bot";
    }

    @Override
    public String getBotToken() {
        return "1054921717:AAFAvaUJvgCcli-iT1xvCSHUHkTTPOqkDxc";
    }
}
