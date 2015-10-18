package myPackage;
import sun.nio.ch.IOUtil;

import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.io.IOException;


/**
 * Created by Paula on 12.10.2015.
 */

public class Parser
{
    public static String readFile(File fin) throws IOException
    {
        FileInputStream fis = new FileInputStream(fin);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        StringBuilder myFile = new StringBuilder();

        String line = null;
        while ((line = br.readLine()) != null){
            myFile.append(line);
        }
        String allFile = myFile.toString();
        br.close();
        return allFile;
    }

    public static ArrayList<String> regExHref(String myString)
    {
        Pattern pattern = Pattern.compile("<a href=\"(.*?)\">");
        Matcher matcher = pattern.matcher(myString);
        ArrayList<String> links = new ArrayList<>();

        while(matcher.find()){
            links.add(matcher.group(1));
        }
        return links;
    }

    public static void main(String[] args) throws IOException
    {
        String htmlPath = ("C:\\Users\\Paula\\Desktop\\plik.html");
        File fin = new File(htmlPath);
        String myHTML;

        myHTML = readFile(fin);

        System.out.println(myHTML);                     //wypisywanie calej zawartosci wczytanego pliku
        System.out.println(regExHref(myHTML));          //wypisanie arraylisty z linkami

    }
}
