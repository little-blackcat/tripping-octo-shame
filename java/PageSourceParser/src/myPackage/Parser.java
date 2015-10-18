package myPackage;
import sun.nio.ch.IOUtil;

import java.io.*;
import java.net.URLConnection;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.io.IOException;
import java.net.URL;


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
            myFile.append(line +"\n");
        }
        String allFile = myFile.toString();
        br.close();
        return allFile;
    }

    public static ArrayList<String> regExHref(String myString)
    {
        Pattern pattern = Pattern.compile("<a href=\"(.*?)\".*>");
        Matcher matcher = pattern.matcher(myString);
        ArrayList<String> links = new ArrayList<>();

        while(matcher.find()){
            links.add(matcher.group(1));
        }
        return links;
    }

    public static void main(String[] args) throws IOException
    {
        String myHTML = null;
        String myWWW = "http://pgs-soft.com";
        int zmPom = 2;
        if (zmPom == 1)
        {
            String htmlPath = ("C:\\Users\\Paula\\Desktop\\plik.html");
            File fin = new File(htmlPath);
            myHTML = readFile(fin);
        }
        else if (zmPom == 2)
        {
            String htmlAddress = myWWW;
            URL userPage = new URL(htmlAddress);
            URLConnection yc = userPage.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream(), "UTF-8"));
            String inputLine;
            StringBuilder a = new StringBuilder();
            while ((inputLine = in.readLine()) != null)
                a.append(inputLine +"\n");
            in.close();

            myHTML = a.toString();
        }

        System.out.println(myHTML);                     //wypisywanie calej zawartosci wczytanego xrodla
        System.out.println(regExHref(myHTML));          //wypisanie arraylisty z linkami

        System.out.println("--------- wyswietlanie z danej domeny --------");
        ArrayList<String> astring = regExHref(myHTML);
        for(String url : astring)
        {
                if(url.startsWith("/") || url.startsWith(myWWW))
                    System.out.println(url);
        }

    }
}
