package myPackage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Paula on 12.10.2015.
 */
public class FileReader extends AbstractReader
{
    public FileReader(String fPath) {
        AbstractReader.filePath = fPath;
    }

    public String fileToString(String filePath) throws IOException
    {
        FileInputStream fis = new FileInputStream(AbstractReader.filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        StringBuilder myFile = new StringBuilder();

        String line = null;
        while ((line = br.readLine()) != null){
            myFile.append(line +"\n");
        }
        String allFile = myFile.toString();
        br.close();
        return allFile;
    }

    public String fileToString() throws IOException
    {
        return fileToString(AbstractReader.filePath);
    }

}
