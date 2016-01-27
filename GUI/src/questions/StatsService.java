package questions;

/**
 * Created by szm on 26.01.2016.
 */
public class StatsService {
    private int goodAnswers = 0;
    private int answers = 0;

    //public StatsService() {}

    public void good() { goodAnswers++; answers++; }
    public void bad() { answers++; }

    public int getGood() { return goodAnswers; }
    public int getAnswers() { return answers; }
    public float getStats() { return ((float)(goodAnswers)/((float)(answers))*100.0f); }

}
