package myPackage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Paula on 12.10.2015.
 */
public class WebPageReader extends AbstractReader
{
    public WebPageReader(String fPath) {
        AbstractReader.filePath = fPath;
    }

    public String fileToString(String filePath) throws IOException
    {
        URL userPage = new URL(filePath);
        URLConnection yc = userPage.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuilder a = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            a.append(inputLine + "\n");
        in.close();

        return a.toString();
    }

    public String fileToString() throws IOException
    {
        return fileToString(AbstractReader.filePath);
    }


}
