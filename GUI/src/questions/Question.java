package questions;

/**
 * Created by Paula on 24.01.2016.
 */
public class Question {
    public String questionText;
    public String[] answers = new String[3];
    public String[] isCorrect = new String[3];

    public Question(String[] iC, String qT, String[] a){
            this.questionText = qT;
            this.answers = a;
            this.isCorrect = iC;
    }

    public void printAll(){
        System.out.println("pytanie: " + this.questionText);
    }


}
