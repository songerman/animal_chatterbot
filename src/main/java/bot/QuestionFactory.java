package bot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class QuestionFactory {

    public static ArrayList<Question> makeQuestions(HashMap<Category, String[]> params) {
        ArrayList<Question> questions = new ArrayList<Question>();
        Set<Category> categories = params.keySet();
        for (Category category : categories) {
            String[] features = params.get(category);
            for (String feature : features) {
                questions.add(new Question(category, feature));
            }
        }
        return questions;
    }

    public static String makeTextQuestion(Category category, String feature) {
        return String.format("%s этого животного - %s?", category.getName(), feature);
    }
}

