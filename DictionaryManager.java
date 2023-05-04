package test;
import java.util.*;
import java.lang.String;

public class DictionaryManager extends Dictionary
{
    public HashMap<String, Dictionary> map;
    private static DictionaryManager dictionaryManager = null;

    private DictionaryManager(String... args) {
        this.map = new HashMap<>();
    }

    public static DictionaryManager get() {
        if (dictionaryManager == null) { dictionaryManager = new DictionaryManager(); }
        return dictionaryManager;
    }

    public boolean query(String...args) {
        String wordToBeSearch = args[args.length - 1];
        boolean wordExists;
        int flag = 0;

        for (int i = 0; i < args.length-1; i++) {
            if (!map.containsKey(args[i]))
            {
                String s = args[i];
                Dictionary dic = new Dictionary(s);
                map.put(s, dic);
            }

            wordExists = map.get(args[i]).query(wordToBeSearch);
            if (wordExists) {
                map.get(args[i]).existsCacheManager.words.add((wordToBeSearch));
                dictionaryManager.existsCacheManager.words.add(wordToBeSearch);
                flag = 1;
            }
            else
                map.get(args[i]).notExistsCacheManager.words.add((wordToBeSearch));
        }
        if (flag == 1)
            return true;
        else {
            dictionaryManager.notExistsCacheManager.words.add(wordToBeSearch);
            return false;
        }
    }

    public boolean challenge(String... args)
    {
        String wordToBeSearch = args[args.length - 1];
        boolean wordExists;
        int flag = 0;

        for (int i = 0; i < args.length-1; i++) {
            if (!map.containsKey(args[i]))
            {
                String s = args[i];
                Dictionary dic = new Dictionary(s);
                map.put(s, dic);
            }

            wordExists = map.get(args[i]).challenge(wordToBeSearch);
            if (wordExists) {
                map.get(args[i]).existsCacheManager.words.add((wordToBeSearch));
                dictionaryManager.existsCacheManager.words.add(wordToBeSearch);
                flag = 1;
            }
            else
                map.get(args[i]).notExistsCacheManager.words.add((wordToBeSearch));
        }
        if (flag == 1)
            return true;
        else {
            dictionaryManager.notExistsCacheManager.words.add(wordToBeSearch);
            return false;
        }
    }
    public int getSize() { return this.map.keySet().size(); }
}
