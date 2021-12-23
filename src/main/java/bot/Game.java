package bot;

import repository.AnimalsRepository;
import repository.DescriptionsRepository;
import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    public static String Rules =
            "Для начала игры введите /start.\n" +
                    "Чтобы узнать правила, введите /help.\n" +
                    "Чтобы узнать счёт, введите /score.\n" +
                    "Чтобы начать сначала, введите /again.\n" +
                    "Чтобы выйти из игры, введите /exit\n";
//    public static Animal[] animals = {
//            new Animal("ворон", "черный", "лес", "маленький"),
//            new Animal("белка", "оранжевый", "лес", "маленький"),
//            new Animal("кит", "синий", "вода", "большой"),
//            //new Animal(),
//    };

    private AnimalsRepository animalsRepository = new AnimalsRepository();

    private DescriptionsRepository descriptionsRepository = new DescriptionsRepository();

    static bot.Animal[] animals;
    private static ArrayList<Question> questions;
    Round currentRound;
    private int roundCount;
    private String userName;
    private int compScore;
    private int userScore;
    private boolean isStarted = false;

    Game(String name) {
        animals = animalsRepository.findAll().toArray(new bot.Animal[0]);
        userName = name;
        questions = QuestionFactory.makeQuestions();
    }

    private void makeScore() {
        if (currentRound.isFinished) {
            if (currentRound.winner.equals("user")) {
                userScore++;
            } else if (currentRound.winner.equals("computer")) {
                compScore++;
            }
        }
    }

    private void updateGame() {
        questions = QuestionFactory.makeQuestions();
        if (currentRound.isFinished) roundCount++;
        currentRound = new Round(questions);
    }

    String startGame() {
        if (currentRound != null) {
            return "Чтобы начать новый раунд, введите /again.";
        }
        currentRound = new Round(questions);
        return "Добро пожаловать в игру. Игрок должен загадать животное, задача компьютера - \nугадать, что это за животное.\n"
                + Rules
                + "Для начала введите любое слово.";
        //return "Введите ваше имя.";
    }

    String repeatGame() {
        if (currentRound != null) {
            if (!currentRound.isFinished) {
                return "Вы еще не закончили раунд. Введите любой текст.";
            }
            updateGame();
            return "Что ж, попробуем ещё раз.\n" + currentRound.play();
        } else {
            return "Вы еще не начали игру. Чтобы начать, введите /start.";
        }
    }

    String playGame(String command) {
        //if (userName == null) {
        if (!isStarted){
            String text = String.format("Игра началась, %s.\nЗагадайте животное.\n", userName);
            isStarted = true;
            return text + currentRound.play();
        } else if (command.equals("да") || command.equals("нет")) {
            currentRound.putAnswer(currentRound.currentQuestion, command);
            //currentRound.askedQuestionCount++;
            String result = currentRound.play();
            if (currentRound.isFinished) {
                makeScore();
            }
            return result;
        }
        return "Я вас не понимаю :( \n Для ответа на вопрос введите \"да\" или \"нет\" ";
    }

    String getScore() {
        return String.format("Компьютер - %s : Пользователь - %d", compScore, userScore);
    }
}
