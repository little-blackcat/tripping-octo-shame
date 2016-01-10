import java.io.*;

import java.util.ArrayList;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws IOException {

        Properties prop = new Properties();
        InputStream input = null;

        input = new FileInputStream("conf.properties");
        prop.load(input);

        String filename = prop.getProperty("filename");
        String outputFilename = prop.getProperty("outputFilename");
        String errorsFilename = prop.getProperty("errorsFilename");
        int rowsPerFile = Integer.parseInt(prop.getProperty("rowsPerFile"));

        CSVLinesReader csvlineReader = new CSVLinesReader(filename, errorsFilename);

        ArrayList<Person> employees = csvlineReader.convertLinesToPersonObjects();

        DuplicatesRemover dr = new DuplicatesRemover();
        ArrayList<Person> listWithoutDuplicates = dr.removeDuplicates(employees);

        SmallerFilesCreator sfc = new SmallerFilesCreator(rowsPerFile, listWithoutDuplicates, outputFilename);
        sfc.createSmallerFiles();
    }
}