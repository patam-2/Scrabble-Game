package test.Model;
import java.io.FileReader;
import java.util.*;
import java.io.IOException;
import java.lang.String;

public class Dictionary
{
    CacheManager existsCacheManager;
    CacheManager notExistsCacheManager;
    BloomFilter bloomFilter;
    String[] files;

    public Dictionary(String... fileNames)
    {
        this.files = new String[fileNames.length];
        this.existsCacheManager = new CacheManager(400, new LRU());
        this.notExistsCacheManager = new CacheManager(100, new LFU());
        this.bloomFilter = new BloomFilter(256, "SHA1", "MD5");
        try
        {
            int i = 0;
            for (String s : fileNames)
            {
                FileReader fr = new FileReader(s);
                Scanner scan = new Scanner(fr);

                while (scan.hasNext())
                {
                    scan.useDelimiter(" ");
                    String word = scan.next();
                    this.bloomFilter.add(word);
                }
                this.files[i] = s;
                i++;
                fr.close();
            }
        }
        catch (IOException exception)
        {
            throw new RuntimeException(exception);
        }
    }

    public boolean query(String s)
    {
        boolean isTheWordInside = existsCacheManager.query(s);
        if (isTheWordInside)
            return true;
        isTheWordInside = notExistsCacheManager.query(s);
        if (isTheWordInside)
            return false;
        isTheWordInside = bloomFilter.contains(s);
        if (!isTheWordInside)
        {
            existsCacheManager.words.remove(s);
            notExistsCacheManager.words.add(s);
        }
        else // the word is Probably exists
        {
            existsCacheManager.words.add(s);
            notExistsCacheManager.words.remove(s);
        }
        return isTheWordInside;
    }

    public boolean challenge(String s)
    {
        try
        {
            for (String st : files)
            {
                if (IOSearcher.search(s, files))
                {
                    existsCacheManager.words.add(s);
                    notExistsCacheManager.words.remove(s);
                    return true;
                }
                existsCacheManager.words.remove(s);
                notExistsCacheManager.words.add(s);
                return false;
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        return false;
    }
}
