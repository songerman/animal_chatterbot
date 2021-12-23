package bot;

import java.util.HashMap;

public class AnimalFactory {
    public static Animal makeAnimal(HashMap<Question, Boolean> answers) {
        HashMap<String, String> description = new HashMap<>();
        Animal animal = new Animal("myAnimal", description);
        if (!answers.isEmpty()) {
            for (Question question : answers.keySet()) {
                if (question != null) {
                    if (answers.get(question)) {
                        animal.description.put(question.category, question.feature);
                        System.out.println(animal.description.toString());
                    }
                }
            }
            return animal;
        }
        return null;
    }
}
