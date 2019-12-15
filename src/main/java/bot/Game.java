package bot;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    public static String Rules =
            "Для начала игры введите /start.\n" +
                    "Чтобы узнать правила, введите /help.\n" +
                    "Чтобы узнать счёт, введите /score.\n" +
                    "Чтобы начать сначала, введите /again.\n" +
                    "Чтобы выйти из игры, введите /exit\n";
    public static Animal[] Animals = {
            new Animal("ворон", "черный", "лес", "маленький"),
            new Animal("белка", "оранжевый", "лес", "маленький"),
            new Animal("кит", "синий", "вода", "большой"),
            //new Animal(),
    };
    public static ArrayList<Question> Questions;
    public static HashMap<Category, String[]> params = new HashMap<>();
    public Round currentRound;
    public int roundCount;
    public String userName;
    public int compScore;
    public int userScore;
    public boolean isStarted = false;
    public Game(String name) {
        params.put(Category.COLOR, new String[]{"белый", "черный", "синий", "оранжевый"});
        params.put(Category.AREA, new String[]{"джунгли", "лес", "пустыня", "вода"});
        params.put(Category.SIZE, new String[]{"большой", "средний", "маленький"});
        userName = name;
        Questions = QuestionFactory.makeQuestions(params);
    }

    public void makeScore() {
        if (currentRound.isFinished) {
            if (currentRound.winner.equals("user")) {
                userScore++;
            } else if (currentRound.winner.equals("computer")) {
                compScore++;
            }
        }
    }

    public void updateGame() {
        Questions = QuestionFactory.makeQuestions(params);
        if (currentRound.isFinished) roundCount++;
        currentRound = new Round(Questions);
    }

    public String startGame() {
        if (currentRound != null) {
            return "Чтобы начать новый раунд, введите /again.";
        }
        currentRound = new Round(Questions);
        return "Добро пожаловать в игру. Игрок должен загадать животное, задача компьютера - \nугадать, что это за животное.\n"
                + Rules
                + "Для начала введите любое слово.";
        //return "Введите ваше имя.";
    }

    public String repeatGame() {
        if (currentRound != null) {
            updateGame();
            return "Что ж, попробуем ещё раз.\n" + currentRound.play();
        } else {
            return "Вы еще не начали игру. Чтобы начать, введите /start.";
        }
    }

    public String playGame(String command) {
        //if (userName == null) {
        if (!isStarted){
            String text = String.format("Игра началась, %s.\nЗагадайте животное.\n", userName);
            isStarted = true;
            return text + currentRound.play();
        } else if (command.equals("да") || command.equals("нет")) {
            currentRound.putAnswer(currentRound.currentQuestion, command);
            currentRound.askedQuestionCount++;
            String result = currentRound.play();
            if (currentRound.isFinished) {
                makeScore();
            }
            return result;
        }
        return "Я вас не понимаю :(";
    }

    public String getScore() {
        return String.format("Компьютер - %s : Пользователь - %d", compScore, userScore);
    }
}
