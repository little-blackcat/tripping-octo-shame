package generator;

import java.util.List;
import java.util.Random;
import questions.ObjectFromFileCreator;
import questions.Question;

public class RandomObjectGenerator {

    private Random randomGenerator;
    List<Question> questions;

    public RandomObjectGenerator(ObjectFromFileCreator obj)
    {
        randomGenerator = new Random();
        questions = obj.getList();
    }

    public Question getRandomQuestion()
    {
        int index = this.randomGenerator.nextInt(this.questions.size());
        Question question = questions.get(index);
        return question;
    }
}
