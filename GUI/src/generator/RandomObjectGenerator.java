package generator;

import java.util.ArrayList;
import java.util.Random;
import questions.CreatingObjectFromFile;
import questions.Question;

public class RandomObjectGenerator {

    private Random randomGenerator;
    ArrayList<Question> questions;

    public RandomObjectGenerator(CreatingObjectFromFile obj)
    {
        randomGenerator = new Random();
        questions = obj.questions;
    }

    public Question getRandomQuestion()
    {
        int index = this.randomGenerator.nextInt(this.questions.size());
        Question question = questions.get(index);
        return question;
    }
}
