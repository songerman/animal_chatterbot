package bot;

import bot.Category;
import bot.QuestionFactory;

public class Question {
    public Category category;
    public String feature;
    public String question;

    public Question(Category category, String feature) {
        this.category = category;
        this.feature = feature;
        this.question = QuestionFactory.makeTextQuestion(category, feature);
    }
}
