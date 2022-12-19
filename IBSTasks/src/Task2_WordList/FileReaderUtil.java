package Task2_WordList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class FileReaderUtil {
    public static ArrayList<String> GetWordsInFile(String filepath) throws FileNotFoundException {
        File file = new File(filepath);
        ArrayList<String> array = new ArrayList<>();

        Scanner scanner = new Scanner(file);
        final String splitRegex = "[,.—!? 0-9\t\n]++";

        while(scanner.hasNextLine())
        {
            Collections.addAll(array, scanner.nextLine().split(splitRegex));
        }
        scanner.close();

        return array;
    }
}
