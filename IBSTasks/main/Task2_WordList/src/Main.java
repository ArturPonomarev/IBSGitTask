package Task2_WordList;

import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        ArrayList<String> wordsArray = null;
        boolean isFileCorrect;
        do
        {
            isFileCorrect = true;
            try
            {
                wordsArray = WordsUtil.ConvertStringToWordsArray(FileReaderUtil.GetFileContent(FileReaderUtil.InputFilePath()));

            }
            catch (FileNotFoundException | StringIndexOutOfBoundsException e)
            {
                isFileCorrect = false;
                System.out.println("Вы ввели некорректный путь к файлу, повторите ввод");
            }
        }while (!isFileCorrect);

        //Массив слов полученный из файла преобразуется в карту слов <Слово, кол-во вхождений>
        var wordsCountMap = WordsUtil.ConvertWordsArrayToWordsMap(wordsArray);

        System.out.println("               Информация о словах");
        System.out.format("%20s | %5s | %1s\n","Слово","Кол-во","%");
        System.out.println("---------------------------------------");

        //Подсчет и вывод статистики слов
        for (var key : wordsCountMap.keySet())
        {
            int currentWordCount = wordsCountMap.get(key);
            double percentage = (double)currentWordCount/(double)wordsArray.size() * 100;
            System.out.format("%20s |  %5d | %.2f%%\n",key,currentWordCount,percentage);
        }

        System.out.println("\n");
        System.out.println("Самое большое количество слов:");
        var frequentWordsCountMap = WordsUtil.FindMostFrequentWords(wordsCountMap);
        for (var key : frequentWordsCountMap.keySet())
        {
            System.out.format("Слово \"%s\" - %d вхождений\n",key,frequentWordsCountMap.get(key));
        }
    }
}
