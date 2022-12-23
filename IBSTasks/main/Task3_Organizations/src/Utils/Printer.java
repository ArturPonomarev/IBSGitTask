package src.Utils;

import src.JsonModels.Security;

public class Printer {
    public static void PrintSecurityInformationForOldSecurities(Security security, String owner)
    {
        System.out.println("\nЦенная бумага:");
        System.out.format("\tКод: %s\n\tДата истечения: %s\n\tВладелец: %s\n",
                security.code,security.date,owner);
    }

    public static void PrintSecurityInformationForCurrencyRequest(Security security)
    {
        System.out.println("\nЦенная бумага:");
        System.out.format("\tid: %s\n\tКод: %s\n",
                security.name,security.code);
    }
}
