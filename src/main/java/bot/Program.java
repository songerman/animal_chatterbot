package bot;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Scanner newscan = new Scanner(System.in);//.useDelimiter(" ");
        MyDialog dialog = new MyDialog();
        while (true) {
            String command = newscan.nextLine();
            PrintOut(dialog.getReaction(command));
        }
        //newscan.close();
    }

    public static void PrintOut(String msg) {
        System.out.println(msg);
    }
}

