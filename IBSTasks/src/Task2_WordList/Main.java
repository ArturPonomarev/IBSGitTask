package Task2_WordList;

import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    final static String RELATIVE_FILE_PATH_START_SYMBOL = ".";

    public static String InputFilePath() throws StringIndexOutOfBoundsException
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите относительный или абсолютный путь к файлу");
        String filePath = scanner.nextLine();

        if (filePath.charAt(0) == '/')
            filePath = RELATIVE_FILE_PATH_START_SYMBOL + filePath;

        return filePath;
    }

    public static void main(String[] args) {

        ArrayList<String> wordsArray = null;
        boolean isFileCorrect;
        do
        {
            isFileCorrect = true;
            try
            {
                wordsArray = WordsUtil.ConvertStringToWords(FileReaderUtil.GetFileContent(InputFilePath()));
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

        //Подсчет и вывод статистики слов, поиск чаще встречаемого слова
        TreeMap<String, Integer> maxWordsCount = new TreeMap<>();
        int max = 0;
        for (var key : wordsCountMap.keySet())
        {
            int currentWordCount = wordsCountMap.get(key);

            if (currentWordCount>max)
            {
                maxWordsCount.clear();
                maxWordsCount.put(key,currentWordCount);
                max = currentWordCount;
            } else if (currentWordCount == max) {
                maxWordsCount.put(key,currentWordCount);
            }

            double percentage = (double)currentWordCount/(double)wordsArray.size() * 100;
            System.out.format("%20s |  %5d | %.2f%%\n",key,currentWordCount,percentage);
        }

        System.out.println("\n");
        System.out.println("Самое большое количество слов:");
        for (var key : maxWordsCount.keySet())
        {
            System.out.format("Слово \"%s\" - %d вхождений\n",key,maxWordsCount.get(key));
        }
    }
}
