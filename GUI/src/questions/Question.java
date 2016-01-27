package questions;

/**
 * Created by Paula on 24.01.2016.
 */
public class Question {
    private String questionText;
    private String[] answers = new String[3];
    private String[] isCorrect = new String[3];

    public Question(String[] iC, String qT, String[] a){
            this.questionText = qT;
            this.answers = a;
            this.isCorrect = iC;
    }

    public void printAll(){
        System.out.println("pytanie: " + this.questionText);
    }

    public void printAnswers() {
        System.out.println(this.isCorrect[0] +' '+ this.isCorrect[1] +' '+ this.isCorrect[2]);
    }

    public String getQuestionText() { return this.questionText; }
    public String[] getAnswers() { return this.answers; }
    public String[] getCorrect() { return this.isCorrect; }


}
