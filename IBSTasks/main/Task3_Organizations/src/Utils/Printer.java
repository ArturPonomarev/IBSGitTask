package src.Utils;

import src.JsonModels.Company;
import src.JsonModels.Security;

public class Printer {
    public static void PrintCompanyInformation(Company company)
    {
        System.out.format("%s - Дата основания %s\n",company.name, DateTimeUtil.ConvertToNeedDateTime(company.founded));
    }

    public static void PrintSecurityInformation(Security security)
    {
        System.out.println("\nЦенная бумага:");
        System.out.format("\tКод: %s\n\tДата истечения: %s\n\tВладелец: %s\n",
                security.code,security.date,security.name);
    }
}
