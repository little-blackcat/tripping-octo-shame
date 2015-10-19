package myPackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by Paula on 12.10.2015.
 */

public class Parser
{

    public static void main(String[] args) throws IOException
    {
        AbstractReader reader;

        java.io.FileReader frconfig = new java.io.FileReader("config.txt");
        Scanner config = new Scanner(frconfig);

        // url czy file?
        String fPath = config.nextLine();
        if (fPath.startsWith("http") || fPath.startsWith("www."))
        {
            reader = new WebPageReader(fPath);
        } else
        {
            reader = new FileReader(fPath);
        }


        ArrayList<String> allLinks = reader.Parse(reader.fileToString());
        ArrayList<String> linksFromCurrentDomain = reader.ParseFromDomain(reader.fileToString());

        // wyswietl ilosc linkow
        System.out.println("Znaleziono w sumie " + allLinks.size() + " linkow.");

        // wyswietlic wszystkie?
        String ifAll = config.nextLine();
        if (ifAll.equals("tak"))
        {
            System.out.println("Wszystkie linki: ");
            for (int currentLink = 0; currentLink < allLinks.size(); ++currentLink)
            {
                System.out.println(currentLink + ": " + allLinks.get(currentLink));
            }
        } else // nie - tylko te z domeny
        {
            System.out.println("Linki z aktualnej domeny: ");
            for (int currentLink = 0; currentLink < linksFromCurrentDomain.size(); ++currentLink)
            {
                System.out.println(currentLink + ": " + linksFromCurrentDomain.get(currentLink));
            }
        }

        // czy wyswietlic zrodlo strony?
        String ifSource = config.nextLine();
        if (ifSource.equals("tak"))
        {
            Integer number = config.nextInt();
            String link;
            if(ifAll.equals("tak")) // linki z domeny
            {
                link = allLinks.get(number);
            }
            else
            {
                link = linksFromCurrentDomain.get(number);
            }

            // wyswietlenie zrodla strony
            System.out.println("Zrodlo " + link + ": ");
            System.out.println(reader.fileToString(link));

        }

    }
}
