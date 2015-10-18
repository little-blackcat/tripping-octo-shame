package myPackage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by Paula on 12.10.2015.
 */

public class Parser
{

    public static void main(String[] args) throws IOException
    {
        AbstractReader r;

        java.io.FileReader frconfig = new java.io.FileReader("config.txt");
        Scanner config = new Scanner(frconfig);

        // url czy file?
        String fPath = config.nextLine();
        if (fPath.startsWith("http") || fPath.startsWith("www."))
        {
            r = new WebPageReader(fPath);
        } else
        {
            r = new FileReader(fPath);
        }


        ArrayList<String> linki = r.Parse(r.fileToString());
        ArrayList<String> linksFromDomain = r.ParseFromDomain(r.fileToString());

        // wyswietl ilosc linkow
        System.out.println("Znaleziono w sumie " + linki.size() + " linkow.");

        // wyswietlic wszystkie?
        String czyWszystkie = config.nextLine();
        if (czyWszystkie.equals("tak"))
        {
            System.out.println("Wszystkie linki: ");
            for (int q = 0; q < linki.size(); ++q)
            {
                System.out.println(q + ": " + linki.get(q));
            }
        } else // nie - tylko te z domeny
        {
            System.out.println("Linki z aktualnej domeny: ");
            for (int q = 0; q < linksFromDomain.size(); ++q)
            {
                System.out.println(q + ": " + linksFromDomain.get(q));
            }
        }

        // czy wyswietlic zrodlo strony?
        String czyZrodlo = config.nextLine();
        if (czyZrodlo.equals("tak"))
        {
            Integer number = config.nextInt();
            String link = new String();
            if(czyWszystkie.equals("tak")) // linki z domeny
            {
                link = linki.get(number);
            }
            else
            {
                link = linksFromDomain.get(number);
            }

            // wyswietlenie zrodla strony
            System.out.println("Zrodlo " + link + ": ");
            System.out.println(r.fileToString(link));

        }

    }
}
