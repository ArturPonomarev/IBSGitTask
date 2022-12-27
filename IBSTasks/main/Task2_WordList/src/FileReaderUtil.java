package Task2_WordList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReaderUtil {

    final static String RELATIVE_FILE_PATH_START_SYMBOL = ".";

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

    public static String InputFilePath() throws StringIndexOutOfBoundsException
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите относительный или абсолютный путь к файлу");
        String filePath = scanner.nextLine();

        if (filePath.charAt(0) == '/')
            filePath = RELATIVE_FILE_PATH_START_SYMBOL + filePath;

        return filePath;
    }
}
