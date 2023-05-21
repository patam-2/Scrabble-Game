package Model;
import java.io.FileReader;
import java.util.*;
import java.io.IOException;


public class IOSearcher
{
     public static boolean search(String word, String... fileNames) throws IOException
    {
        for (String st : fileNames)
        {
            FileReader fileReader = new FileReader(st);
            Scanner scan = new Scanner(fileReader);

            while (scan.hasNext())
            {
                String line = scan.nextLine();
                if (line.contains(word))
                {
                    fileReader.close();
                    return true;
                }
            }
            fileReader.close();
        }
        return false;
    }
}
