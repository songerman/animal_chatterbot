package config;

import com.petersamokhin.bots.sdk.objects.Message;


public class User {
    public Long Id;
    public String Name;
    public String Platform;

    //vk user
    public User(Message msg) {
        if (msg != null) {
            Id = msg.authorId().longValue();
            Name = "https://vk.com/id" + msg.authorId();
            Platform = "VK";
        }
    }

    //tg user
    public User(org.telegram.telegrambots.meta.api.objects.Message msg){
        if (msg != null) {
            Id = msg.getChatId();
            Name = msg.getFrom().getFirstName();
            Platform = "Tg";
        }
    }

    //classic user
    public User(Long id, String name) {
        Id = id;
        Name = name;
        Platform = "Console";
    }
}

