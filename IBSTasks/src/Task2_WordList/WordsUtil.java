package Task2_WordList;


import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

public class WordsUtil {

    private static final String SPLIT_REGEX = "[^А-Яа-я]+";

    public static ArrayList<String> ConvertStringToWordsArray(String string)
    {
        ArrayList<String> array = new ArrayList<>();
        Collections.addAll(array, string.split(SPLIT_REGEX));
        return array;
    }

    public static TreeMap<String,Integer> ConvertWordsArrayToWordsMap(ArrayList<String> wordsArray)
    {
        TreeMap<String,Integer> wordsCountMap = new TreeMap<>();
        for (var str : wordsArray)
        {
            wordsCountMap.merge(str, 1, Integer::sum);
        }
        return wordsCountMap;
    }

    public static TreeMap<String,Integer> FindMostFrequentWords(TreeMap<String,Integer> wordsMap)
    {
        var frequentWordsMap = new TreeMap<String,Integer>();
        int max = 0;

        for (var key : wordsMap.keySet())
        {
            int currentWordCount = wordsMap.get(key);

            if (currentWordCount>max)
            {
                frequentWordsMap.clear();
                frequentWordsMap.put(key,currentWordCount);
                max = currentWordCount;
            } else if (currentWordCount == max) {
                frequentWordsMap.put(key,currentWordCount);
            }

        }

        return frequentWordsMap;
    }
}
