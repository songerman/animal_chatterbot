package vkbot;

import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.objects.Message;
import config.User;
import config.Users;
import dialog.Dialog;

public class Bot {
    private Group group;

    public Bot(int vkGroupId, String vkToken) {
        group = new Group(vkGroupId, vkToken);
    }

    public void InitCallbacks() {
        group.onMessage(message -> {
            if(message.getText()!=null) {
                User user = new User(message);
                Users.checkUser(user);
                String reply;
                if (!message.isSimpleTextMessage()) {
                    reply = "Я вас не понимаю. Пожалуйста, отправьте сообщение, содержащее только текст.";
                } else {
                    reply = Dialog.makeReply(message.getText(), user);
                }
                sendMsg(message.authorId(), reply);
            }
        });
    }

    public synchronized void sendMsg(int authorId, String text) {
        new Message()
                .from(group)
                .to(authorId)
                .text(text)
                //.photo("C:\\Users\\vlada\\Desktop\\animal_chatterbot\\src\\main\\resources\\pic.jpg")
                .send();
    }
}
