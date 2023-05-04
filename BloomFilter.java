package test;
import java.util.*;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class BloomFilter
{
    int size;
    public BitSet bites;
    public LinkedList<MessageDigest> algs;

    public BloomFilter(int size, String... strings)
    {
        this.size = size;
        this.bites = new BitSet(size);
        this.algs = new LinkedList<>();
        try
        {
            for (String s :  strings)
            {
                this.algs.add(MessageDigest.getInstance(s));
            }
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void add(String s)
    {
        for (MessageDigest digest : algs)
        {
            byte[] hashBytes = digest.digest(s.getBytes());
            int hashIndex = Math.abs(new BigInteger(hashBytes).intValue()) % bites.size();
            bites.set(hashIndex, true);
        }
    }

    public boolean contains(String s)
    {
        for (MessageDigest digest : algs) // runs on all the hash functions we hold
        {
            byte[] hashBytes = digest.digest(s.getBytes());
            int hashIndex = Math.abs(new BigInteger(hashBytes).intValue()) % bites.size();
            if (!bites.get(hashIndex))
                return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        StringBuilder bit = new StringBuilder();
        for (int i = 0; i < bites.length(); i++)
        {
            if (bites.get(i) == true)
                bit.append("1");

            else
                bit.append("0");
        }
        return bit.toString();
    }
}
