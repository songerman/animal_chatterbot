package bot;

import java.util.HashMap;
import java.util.Scanner;

public class Program {
    public static HashMap<Long, MyDialog> users = new HashMap<>();
    public static Scanner newscan = new Scanner(System.in);
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        //.useDelimiter(" ");
        String name = changeUser();
        Long id = Integer.toUnsignedLong(name.hashCode());
        while (true) {
            String command = newscan.nextLine();
            if (command.equals("/changeuser")) {
                name = changeUser();
                id = Integer.toUnsignedLong(name.hashCode());
            } else {
                System.out.println(users.get(id).getReaction(command));
            }
        }
        //newscan.close();
    }

    public static String changeUser(){
        System.out.println("Введите ваше имя");
        String name = newscan.nextLine();
        Long id = Integer.toUnsignedLong(name.hashCode());
        System.out.println(id);
        if (!users.containsKey(id)){
            users.put(id, new MyDialog(id, name));
        }
        System.out.println("Для начала введите /start");
        return name;
    }
}

