package Task2_WordList;

import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<String> wordsArray = FileReader.GetWordsInFile("./IBSTasks/Resources/words.txt");
        Collections.sort(wordsArray);
        HashMap<String,Integer> wordsCountMap = new HashMap<>();

        for (var str : wordsArray)
        {
            wordsCountMap.merge(str, 1, Integer::sum);
        }

        System.out.println("               Информация о словах");
        System.out.format("%20s | %5s | %1s\n","Слово","Кол-во","%");
        System.out.println("---------------------------------------");
        HashMap<String, Integer> maxWordsCount = new HashMap<>();
        int max = 0;
        for (var key : wordsCountMap.keySet())
        {
            int wordCount = wordsCountMap.get(key);

            if (wordCount>max)
            {
                maxWordsCount.clear();
                maxWordsCount.put(key,wordCount);
                max = wordCount;
            } else if (wordCount == max) {
                maxWordsCount.put(key,wordCount);
            }

            double percentage = (double)wordCount/(double)wordsArray.size() * 100;
            System.out.format("%20s |  %5d | %.2f%%\n",key,wordCount,percentage);
        }

        System.out.println("\n");
        System.out.println(maxWordsCount);
    }
}
