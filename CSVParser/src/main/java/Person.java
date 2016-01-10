import java.util.Calendar;

public class Person {

    Pesel pesel;
    String name;
    String surname;

    public Person(Pesel newPesel, String newName, String newSurname){
        this.pesel = newPesel;
        this.name = newName;
        this.surname = newSurname;
    }
}
