package questions;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ObjectFromFileCreator {

    private List<Question> questions = new ArrayList<>();

    //public void convertToObject() throws IOException{

    public ObjectFromFileCreator(String path) throws IOException {
        File currentFile;
        FilesFromDirectory ffd = new FilesFromDirectory(path);

        for(int i = 0; i < ffd.listOfFiles.size(); i++){
            currentFile = ffd.listOfFiles.get(i);

            if(currentFile.isFile()) {
                BufferedReader inputStream = null;
                try {
                    inputStream = new BufferedReader(
                            new FileReader(currentFile));

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

    public List<Question> getList() { return this.questions; }
}
