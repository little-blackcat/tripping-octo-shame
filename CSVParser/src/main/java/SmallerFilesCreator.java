import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SmallerFilesCreator {

    String nameOfFiles;
    int fileSize;
    int listSize;
    int numberOfFiles;
    ArrayList<Person> listToSplit;

    public SmallerFilesCreator(int sizeOfSmallFile, ArrayList<Person> list, String filename) throws IOException {
        this.nameOfFiles = filename;
        this.fileSize = sizeOfSmallFile;
        this.listToSplit = list;
        this.listSize = list.size();
        this.numberOfFiles = listSize / this.fileSize;
        if (this.listSize % this.fileSize > 0)
            this.numberOfFiles++;
    }

    // fileSize_number.csv
    void createSmallerFiles() throws IOException {

        File f;
        FileWriter fw;

        for(int q=0; q<this.numberOfFiles; ++q) {
            f = new File(this.nameOfFiles + "_" + Integer.toString(fileSize)+"_"+Integer.toString(q)+".csv");
            fw = new FileWriter(f.getAbsoluteFile());

            for(int w=q*this.fileSize; w<((q*this.fileSize)+this.fileSize); ++w) {
                if(w>=this.listSize) break;
                fw.write(this.listToSplit.get(w).pesel.wholePesel+';'+this.listToSplit.get(w).surname+';'+this.listToSplit.get(w).name+'\n');
            }
            fw.close();
        }
    }
}
