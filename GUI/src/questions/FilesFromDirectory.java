package questions;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Paula on 24.01.2016.
 */
public class FilesFromDirectory {

    protected ArrayList<File> listOfFiles;

    public FilesFromDirectory(String dir) {
        File directory = new File(dir);
        this.listOfFiles = new ArrayList<>(Arrays.asList(directory.listFiles()));
    }
}
