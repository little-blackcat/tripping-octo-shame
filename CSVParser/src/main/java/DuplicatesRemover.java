import java.util.ArrayList;

public class DuplicatesRemover {

    public DuplicatesRemover(){}

    ArrayList<Person> removeDuplicates(ArrayList<Person> listOfPersons){
        int thereAreDuplicates;
        ArrayList<Person> listOfPersonsWithoutDuplicates = new ArrayList<Person>();
        for (int i = 0; i < listOfPersons.size(); i++){
            thereAreDuplicates = 0;
            for (int j = 0; j < listOfPersonsWithoutDuplicates.size(); j++){
                if(listOfPersons.get(i).pesel.wholePesel.equals(listOfPersonsWithoutDuplicates.get(j).pesel.wholePesel)){
                    thereAreDuplicates ++;
                }
            }
            if (thereAreDuplicates == 0){
                listOfPersonsWithoutDuplicates.add(listOfPersons.get(i));
            }
        }
        return listOfPersonsWithoutDuplicates;
    }
}
