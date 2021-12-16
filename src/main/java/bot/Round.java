package bot;

import java.util.*;

public class Round {
    public boolean isFinished;
    public int askedQuestionCount;
    public int questionCount;
    public Question currentQuestion;
    public ArrayList<Question> questions;
    public HashMap<Question, Boolean> answers = new HashMap<>();
    public String winner;
    public HashMap<Category, Boolean> repeatedCategories = new HashMap<>();

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
            if (!repeatedCategories.containsKey(question.category.getName())) {
                repeatedCategories.put(question.category, Boolean.TRUE);
            }
        } else {
            answers.put(question, Boolean.FALSE);
        }
    }

    public String play() {
        Animal animal = guessAnimal(answers, Game.animals);
        if (animal != null) {
            isFinished = true;
            winner = "computer";
            return String.format("Дайте-ка подумать...\nЗагаданное животное - %s.", animal.name);
        } else {
            while (askedQuestionCount < questionCount) {
                currentQuestion = getNextQuestion(questions, askedQuestionCount);
                askedQuestionCount++;

                if (!repeatedCategories.containsKey(currentQuestion.category)) {
                    return (currentQuestion.question);
                }
            }
        }

        isFinished = true;
        winner = "user";
        return "Я не знаю такое животное :(";
    }
}



