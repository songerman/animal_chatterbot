import bot.MyDialog;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;

import java.util.Scanner;
import java.util.logging.Level;



public class Bot extends TelegramLongPollingBot {
    MyDialog dialog = new MyDialog();
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        if (message != null && message.hasText()) {
            if (message.getText().equals("/exit")){
                dialog = new MyDialog();
                sendMsg(message, "До свидания.");
            }
            else {
                String reply = dialog.getReaction(message.getText());
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
