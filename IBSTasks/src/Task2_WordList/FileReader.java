package Task2_WordList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class FileReader {
    public static ArrayList<String> GetWordsInFile(String filepath) throws FileNotFoundException {
        File file = new File(filepath);
        ArrayList<String> array = new ArrayList<>();

        Scanner scanner = new Scanner(file);
        String splitRegex = "[,.—–!? ]+";

        while(scanner.hasNextLine())
        {
            Collections.addAll(array, scanner.nextLine().split(splitRegex));
        }
        scanner.close();

        return array;
    }
}
