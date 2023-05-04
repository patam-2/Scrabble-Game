package test;
import java.util.*;

public class LFU implements CacheReplacementPolicy
{
    public Map<String, Integer> map;
    public int frecuncy;

    public LFU()
    {
        this.frecuncy = 1;
        this.map = new LinkedHashMap<String, Integer>();
    }

    public void add(String s)
    {
        if (map.containsKey(s))
        {
            map.put(s, frecuncy + 1);
        }
        else
            map.put(s, frecuncy);
    }


    public String remove()
    {
        String stringToRemove = null;
        int minFrequency = Integer.MAX_VALUE;

        for (Map.Entry<String, Integer> entry : map.entrySet())
        {
            if (entry.getValue() < minFrequency)
            {
                minFrequency = entry.getValue();
                stringToRemove = entry.getKey();
            }
        }
        map.remove(stringToRemove, minFrequency);

        return stringToRemove;
    }











}




    //public static LinkedHashMap<Integer, String> map;
//    public static LinkedHashMap<Integer, Integer> frequencyMap;
//    public int key;
//
//    public LFU()
//    {
//        this.key = 0;
//        this.map = new LinkedHashMap<Integer, String>();
//        this.frequencyMap = new LinkedHashMap<Integer, Integer>();
//    }
//
//    public void add(String s)
//    {
//        if (map.isEmpty())
//        {
//            frequencyMap.put(key, 1);
//            map.put(key, s);
//        }
//
//        else if (!map.containsValue(s))
//        {
//            map.put(key, s);
//            frequencyMap.put(key, 1);
//        }
//
//        else // s exists in q already
//        {
//            int temp = findValIndex(map, s);
//            int frecuncy = frequencyMap.get(temp);
//            frequencyMap.put(key, frecuncy + 1);
//        }
//        key++;
//    }
//
//    int min = Integer.MAX_VALUE;
//
//    public int findSmallestFrec(LinkedHashMap<Integer, Integer> frec)
//    {
//        for (int value : frec.values())
//        {
//            if (value < min)
//            {
//                min = value;
//            }
//        }
//        return min;
//    }
//
//    public int findValIndex(LinkedHashMap<Integer, String> frec, String tempS)
//    {
//        int i = 0;
//        int index = i;
//
//       for (String value : frec.values())
//       {
//           if (value.equals(tempS))
//               return index = i;
//           i++;
//       }
//       return -1;
//    }
//
//
//    public String remove()
//    {
////        if ()
////        {
////
////        }
//        return null;
//    }
