package Model;
import java.util.*;

public class CacheManager
{
	public int size;
    public CacheReplacementPolicy crp;
    public HashSet<String> words;

    public CacheManager(int size, CacheReplacementPolicy crp)
    {
        this.size = size;
        this.crp = crp;
        this.words = new HashSet<String>();
    }

    public boolean query(String word)
    {
        for (String s : this.words)
        {
            if (s.equals(word))
                return true;
        }
        return false;
    }

    public void add(String word)
    {
        if (size == words.size())
            this.words.remove(this.crp.remove());

        this.crp.add(word);
        this.words.add(word);
    }
}
