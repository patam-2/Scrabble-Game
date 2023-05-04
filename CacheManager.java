package test;
import java.util.*;

public class CacheManager
{
	int size;
    CacheReplacementPolicy crp;
    HashSet<String> words;

    public CacheManager(int size, CacheReplacementPolicy crp)
    {
        this.size = size;
        this.crp = crp;
        this.words = new HashSet<String>();
    }

    public boolean query(String word)
    {
        for (String s : words)
        {
            if (s.equals(word))
                return true;
        }
        return false;
    }

    public void add(String word)
    {
        if (size == words.size())
            words.remove( crp.remove());

        crp.add(word);
        words.add(word);
    }
}
