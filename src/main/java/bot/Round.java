package bot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Round {
    public boolean isFinished;
    public int askedQuestionCount;
    public int questionCount;
    public Question currentQuestion;
    public ArrayList<Question> questions;
    public HashMap<Question, Boolean> answers = new HashMap<>();
    public String winner;

    public Round(ArrayList<Question> questions) {
        this.questions = questions;
        Collections.shuffle(questions);
        questionCount = questions.size();
    }

    public Animal guessAnimal(HashMap<Question, Boolean> answers, Animal[] animals) {
        Animal myAnimal = AnimalFactory.makeAnimal(answers);
        for (Animal animal : animals) {
            if (animal.isSimilar(myAnimal)) {
                return animal;
            }
        }
        return null;
    }

    public Question getNextQuestion(ArrayList<Question> questions, int number) {
        return questions.get(number);
    }

    public void putAnswer(Question question, String answer) {
        if (answer.equals("да")) {
            answers.put(question, Boolean.TRUE);
        } else {
            answers.put(question, Boolean.FALSE);
        }
    }

    public String play() {
        if (askedQuestionCount <= questionCount) {
            Animal animal = guessAnimal(answers, Game.Animals);
            if (animal != null) {
                isFinished = true;
                winner = "computer";
                return String.format("Дайте-ка подумать...\nЗагаданное животное - %s.", animal.name);
            } else if (askedQuestionCount < questionCount) {
                currentQuestion = getNextQuestion(questions, askedQuestionCount);
                return (currentQuestion.question);
            }
        }
        isFinished = true;
        winner = "user";
        return "Я не знаю такое животное :(";
    }
}
