package config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Info {
    private static FileReader reader;
    static {
        try {
            reader = new FileReader("C:\\Users\\vlada\\Desktop\\animal_chatterbot\\config.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Scanner scan = new Scanner(reader);
    private static String TgToken= scan.nextLine().split(":")[1];
    private static String TgName = scan.nextLine().split(":")[1];
    private static String VkToken = scan.nextLine().split(":")[1];
    private static int VkGroupId = Integer.parseInt(scan.nextLine().split(":")[1]);

    public static String getTelegramToken() {
        return TgToken;
    }

    public static String getTelegramName() {
        return TgName;
    }

    public static String getVkToken() {
        return VkToken;
    }

    public static int getVkGroupId() {
        return VkGroupId;
    }
}