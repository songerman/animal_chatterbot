package bot;

import java.util.HashMap;

public class AnimalFactory {
    public static Animal makeAnimal(HashMap<Question, Boolean> answers) {
        Animal animal = new Animal("myAnimal", "", "", "");
        if (!answers.isEmpty()) {
            for (Question question : answers.keySet()) {
                if (question != null) {
                    if (answers.get(question)) {
                        question.category.getSetter().accept(animal, question.feature);
                    }
                }
            }
            return animal;
        }
        return null;
    }
}
