package bot;

import repository.DescriptionsRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class QuestionFactory {

    public static ArrayList<Question> makeQuestions(){
        List<String> categories = DescriptionsRepository.getAllCategories();
        HashMap<String, List<String>> params = new HashMap<>();
        for (String cat : categories) {
            params.put(cat, DescriptionsRepository.getAll(cat));
        }
        ArrayList<Question> questions = new ArrayList<>();

        for (String category : categories) {
            List<String> features = params.get(category);
            for (String feature : features) {
                questions.add(new Question(category, feature));
            }
        }
        return questions;
    }

//    public static String makeTextQuestion(Category category, String feature) {
//        return String.format("%s этого животного - %s?", category.getName(), feature);
//    }

    public static String makeTextQuestion(String category, String feature) {
        return String.format("%s этого животного - %s?", category, feature);
    }
}

