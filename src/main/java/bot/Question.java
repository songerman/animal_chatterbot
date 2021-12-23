package bot;

public class Question {
    public String category;
    public String feature;
    public String question;

    public Question(String category, String feature) {
        this.category = category;
        this.feature = feature;
        this.question = QuestionFactory.makeTextQuestion(category, feature);
    }
}
