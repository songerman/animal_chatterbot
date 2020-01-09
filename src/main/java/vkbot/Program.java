package vkbot;

import config.Info;

public class Program {

    public static void main(String[] args) {
        vkbot.Bot bot = new Bot(Info.getVkGroupId(), Info.getVkToken());
        bot.InitCallbacks();
    }
}