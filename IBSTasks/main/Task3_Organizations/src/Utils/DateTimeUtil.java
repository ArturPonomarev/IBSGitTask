package src.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private final static String JSON_DATE_FORMAT = "d.MM.yyyy";
    private final static String STANDART_DATE_FORMAT = "dd/MM/yy";

    public static LocalDate ConvertToNeedDateTime(String stringDate)
    {
        return ConvertToNeedDateTime(stringDate,STANDART_DATE_FORMAT);
    }

    public static LocalDate ConvertToNeedDateTime(String stringDate, String needFormat)
    {
        LocalDate date = LocalDate.parse(stringDate, DateTimeFormatter.ofPattern(JSON_DATE_FORMAT));
        date.format(DateTimeFormatter.ofPattern(needFormat));
        return date;
    }
}
