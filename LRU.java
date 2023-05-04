package test;
import java.util.*;

public class LRU implements CacheReplacementPolicy
{
    public Deque<String> q;

    public LRU()
    {
        this.q = new LinkedList<String>();
    }

    public void add(String s)
    {
        if (q.isEmpty())
            q.addFirst(s);

        else if (q.contains(s))
        {
            q.remove(s);
            q.addLast(s);
        }
        else
            q.addLast(s);
    }

    public String remove()
    {
        if (!q.isEmpty())
            return q.removeFirst();
        return null;
    }

    public boolean equals(String s)
    {
        return (s.equals(this));
    }
}
