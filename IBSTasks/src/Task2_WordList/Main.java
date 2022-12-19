package Task2_WordList;

import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    final static String RELATIVE_FILE_PATH_START_SYMBOL = ".";

    public static String InputFilePath()
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
                wordsArray = FileReaderUtil.GetWordsInFile(InputFilePath());
            }
            catch (FileNotFoundException e)
            {
                isFileCorrect = false;
                System.out.println("Вы ввели некорректный путь к файлу, повторите ввод");
            }
        }while (!isFileCorrect);


        TreeMap<String,Integer> wordsCountMap = new TreeMap<>();

        //Массив слов полученный из файла преобразуется в карту слов <Слово, кол-во вхождений>
        for (var str : wordsArray)
        {
            wordsCountMap.merge(str, 1, Integer::sum);
        }


        System.out.println("               Информация о словах");
        System.out.format("%20s | %5s | %1s\n","Слово","Кол-во","%");
        System.out.println("---------------------------------------");
        //Подсчет и вывод статистики слов, поиск чаще встречаемого слова
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
        System.out.println("Самое большое количество слов:");
        for (var key : maxWordsCount.keySet())
        {
            System.out.format("Слово \"%s\" - %d вхождений\n",key,maxWordsCount.get(key));
        }
    }
}
