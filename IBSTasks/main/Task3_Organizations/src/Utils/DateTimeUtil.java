package src.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeUtil {

    public static LocalDate getDateFromStringFormat(String stringDate, String stringDateFormat)
    {
        return LocalDate.parse(stringDate, DateTimeFormatter.ofPattern(stringDateFormat));
    }

    public static String getFormattedLocalDate(LocalDate date, String formatPatter)
    {
        return date.format(DateTimeFormatter.ofPattern(formatPatter));
    }

    public static boolean CheckDateFormatIsCorrect(String stringToCheck, String correctFormat)
    {
        boolean isCorrect = true;
        try
        {
            LocalDate.parse(stringToCheck, DateTimeFormatter.ofPattern(correctFormat));
        }
        catch (DateTimeParseException e)
        {
            isCorrect = false;
        }

        return isCorrect;
    }
}
