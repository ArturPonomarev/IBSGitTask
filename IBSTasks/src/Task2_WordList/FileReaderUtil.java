package Task2_WordList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReaderUtil {
    public static String GetFileContent(String filepath) throws FileNotFoundException {
        File file = new File(filepath);
        StringBuilder fileContent = new StringBuilder();

        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine())
        {
            fileContent.append(scanner.nextLine());
        }
        scanner.close();

        return fileContent.toString();
    }
}
