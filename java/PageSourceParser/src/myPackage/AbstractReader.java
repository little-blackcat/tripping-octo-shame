package myPackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Paula on 12.10.2015.
 */
public abstract class AbstractReader
{
    public static String filePath;

    public abstract String fileToString(String filePath) throws IOException;
    public abstract String fileToString() throws IOException;

    public static ArrayList<String> Parse(String myString)
    {
        Pattern pattern = Pattern.compile("<a href=\"(.*?)\".*>");
        Matcher matcher = pattern.matcher(myString);
        ArrayList<String> links = new ArrayList<>();

        while(matcher.find()){
            links.add(matcher.group(1));
        }
        return RemoveDuplicates(links);
    }

    public static ArrayList<String> ParseFromDomain(String myDomain)
    {
        ArrayList<String> urls = new ArrayList<>();
        ArrayList<String> links = Parse(myDomain);
        for(String url : links)
        {
            if(url.startsWith("/"))
                urls.add(url);
        }
        return RemoveDuplicates(urls);
    }

    public static ArrayList<String> RemoveDuplicates(ArrayList<String> ax)
    {
        ArrayList<String> al = ax;
        HashSet<String> hs = new HashSet<String>();
        hs.addAll(al);
        al.clear();
        al.addAll(hs);
        return al;
    }
}
