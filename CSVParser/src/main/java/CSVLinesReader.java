import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class CSVLinesReader {

    String separator = ";";
    Scanner sc;
    String errorFileName;

    public CSVLinesReader(String fn, String efn){
        File file = new File(fn);
        try{
        this.sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku o takiej nazwie!");
        }
        this.errorFileName = efn;
    }

    ArrayList<String> readLinesFromCSVFile(Scanner sc) {
        ArrayList<String> listOfLines = new ArrayList<String>();
        while (sc.hasNextLine()) {
            listOfLines.add(sc.nextLine());
        }
        return listOfLines;
    }

    ArrayList<Person> convertLinesToPersonObjects() throws IOException {
        ArrayList<String> listOfLines = readLinesFromCSVFile(this.sc);
        ArrayList<Person> listOfPersons = new ArrayList<Person>();
        File f = new File(this.errorFileName + ".csv");
        FileWriter fw = new FileWriter(f.getAbsoluteFile());
        int errorID = 0;

        for (int i = 1; i < listOfLines.size(); i++) {
            String[] elements = listOfLines.get(i).split(separator);
            Pesel pesel = new Pesel(elements[0]);

            if (pesel.peselIsCorrect()) {
                listOfPersons.add(new Person(pesel, elements[1], elements[2]));
            } else {
                errorID += 1;
                fw.write(listOfLines.get(i) + '\n');
            }
        }
        fw.close();
        return listOfPersons;
    }
}

