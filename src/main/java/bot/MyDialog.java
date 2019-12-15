package bot;

public class MyDialog {
    public Game game;
    public Long userId;
    public String userName;
    public MyDialog(Long id, String name) {
        String s = "Добро пожаловать в игру. Игрок должен загадать животное, задача компьютера - \nугадать, что это за животное.\n" + Game.Rules;
        //bot.Program.PrintOut("Добро пожаловать в игру. Игрок должен загадать животное, задача компьютера - \nугадать, что это за животное.");
        //bot.Program.PrintOut(Game.Rules);
        userId = id;
        userName = name;
        game = new Game(name);
    }

    private static boolean isCommand(String text) {
        return text.startsWith("/");
    }

    public String getReaction(String command) {
        if (command.equals("/start")) {
            return game.startGame();
        } else if (command.equals("/again")) {
            return game.repeatGame();
        } else if (command.equals("/score")) {
            return game.getScore();
        } else if (command.equals("/exit")) {
            //return "До свидания.";
            System.exit(0);
        } else if (command.equals("/help")) {
            return game.Rules;
        } else if (game.currentRound == null) {
            return "Такой команды не существует...";
        } else if (!game.currentRound.isFinished) {
            return game.playGame(command);
        }
        return ("Для следующего раунда введите /again. Для выхода из игры введите /exit.");
    }
}
