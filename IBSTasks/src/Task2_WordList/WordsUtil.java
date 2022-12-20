package Task2_WordList;


import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

public class WordsUtil {

    private static final String SPLIT_REGEX = "[^А-Яа-я]+";

    public static ArrayList<String> ConvertStringToWords(String string)
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
}
