import java.util.Calendar;

/**
 * Created by Paula on 06.01.2016.
 */
public class Pesel {

    String wholePesel;
    int age;

    public Pesel(String newPesel){
        this.wholePesel = newPesel;
    }

    int getYear(){
        int year = Character.getNumericValue(this.wholePesel.charAt(0))*10 + Character.getNumericValue(this.wholePesel.charAt(1));
        return year;
    }

    boolean peselIsCorrect(){
        int sum = Character.getNumericValue(this.wholePesel.charAt(0)) + (3*Character.getNumericValue(this.wholePesel.charAt(1))) + (7*Character.getNumericValue(this.wholePesel.charAt(2)))
                + (9*Character.getNumericValue(this.wholePesel.charAt(3))) + Character.getNumericValue(this.wholePesel.charAt(4)) + (3*Character.getNumericValue(this.wholePesel.charAt(5)))
                + (7*Character.getNumericValue(this.wholePesel.charAt(6))) + (9*Character.getNumericValue(this.wholePesel.charAt(7))) + Character.getNumericValue(this.wholePesel.charAt(8))
                + (3*Character.getNumericValue(this.wholePesel.charAt(9))) + Character.getNumericValue(this.wholePesel.charAt(10));

        if (sum%10 == 0)
            return true;
        else
            return false;
    }

    int ageCalculator(){
        int birthYear = this.getYear() + 1900;
        age = Calendar.getInstance().get(Calendar.YEAR) - birthYear;
        return age;
    }
}
