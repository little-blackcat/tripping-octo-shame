package questions;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class CreatingObjectFromFile {

    public ArrayList<Question> questions = new ArrayList<>();

    public CreatingObjectFromFile() {}

    public void convertToObject() throws IOException{
        File currentFile;
        FilesFromDirectory ffd = new FilesFromDirectory("questions");

        for(int i = 0; i < ffd.listOfFiles.size(); i++){
            currentFile = ffd.listOfFiles.get(i);

            if(currentFile.isFile()) {
                BufferedReader inputStream = null;
                try {
                    inputStream = new BufferedReader(
                            new FileReader(currentFile));

                    System.out.println(":)");

                    String[] iC = inputStream.readLine().split(" ");
                    String qT = inputStream.readLine();
                    String[] a = {inputStream.readLine(), inputStream.readLine(), inputStream.readLine()};
                    questions.add(new Question(iC, qT, a));

                }
                finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
            }
        }
    }
}
