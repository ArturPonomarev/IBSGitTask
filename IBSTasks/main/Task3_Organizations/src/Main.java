package src;

import src.JsonModels.CompanyList;
import src.Utils.DateTimeUtil;
import src.Utils.JsonUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private final static int TOP_EDGE_YEAR = 2070;
    private final static int YEARS_IN_ONE_CENTURY = 100;
    private final static String COMPANIES_JSON_PATH = "./IBSTasks/main/resources/Organizations.json";
    private final static String JSON_DATE_FORMAT = "d.MM.yyyy";
    private final static String COMPANY_PRINT_DATE_FORMAT = "dd/MM/yy";
    private final static String[] NEED_USER_INPUT_FORMATS = {"dd.MM.yyyy","dd.MM.yy","dd/MM/yyyy","dd/MM/yy"};
    private final static String[] CURRENCY_NAMES = {"RUB","EU","USD"};

    public static void main(String[] args) throws IOException {

        CompanyList companyList = JsonUtil.DeserializeFile(COMPANIES_JSON_PATH,CompanyList.class);

        //Вывод списка компаний
        System.out.println("Список компаний:");
        companyList.companies
                .forEach(company -> System.out.format("%s - Дата основания %s\n",company.name,
                        DateTimeUtil.getFormattedLocalDate(DateTimeUtil.getDateFromStringFormat(company.founded,JSON_DATE_FORMAT),
                                COMPANY_PRINT_DATE_FORMAT)));

        //Вывод просроченных ценных бумаг
        System.out.print("\nСписок просроченых ценных бумаг:");
        int oldSecuritySum = 0;

        for (var company : companyList.companies)
        {
            for (var security : company.securities)
            {
                if (DateTimeUtil.getDateFromStringFormat(security.date,JSON_DATE_FORMAT).isBefore(LocalDate.now()))
                {
                    System.out.println("\nЦенная бумага:");
                    System.out.format("\tКод: %s\n\tДата истечения: %s\n\tВладелец: %s\n",
                            security.code,security.date,company.name);
                    oldSecuritySum++;
                }
            }
        }
        System.out.println("Количество просроченых ценных бумаг: " + oldSecuritySum);


        //Обработка ввода пользователя
        Scanner scanner = new Scanner(System.in);

        String userInput;
        do
        {
            System.out.println("\nВведите цифру для действий:");
            System.out.println("1 - Вывести все организации, созданные после определенной даты.");
            System.out.println("2 - Вывести все ценные бумаги в определенной валюте");
            System.out.println("9 - Закрыть приложение");
            userInput = scanner.nextLine();

            switch (userInput)
            {
                case "1":
                    System.out.format("Введите дату в одном из следующих форматов: %s; %s; %s; %s;\n",
                            NEED_USER_INPUT_FORMATS[0], NEED_USER_INPUT_FORMATS[1], NEED_USER_INPUT_FORMATS[2], NEED_USER_INPUT_FORMATS[3]);

                    userInput = scanner.nextLine();
                    String userFormat = "";
                    do {
                        for (var correctFormat : NEED_USER_INPUT_FORMATS)
                        {
                            if (DateTimeUtil.CheckDateFormatIsCorrect(userInput,correctFormat))
                            {
                                userFormat = correctFormat;
                                break;
                            }
                        }

                        if (userFormat.equals(""))
                            System.out.println("Введеный формат не корректен, повторите ввод.");

                    }while (userFormat.equals(""));

                    LocalDate userInputDateTime = DateTimeUtil.getDateFromStringFormat(userInput, userFormat);
                    if (userInputDateTime.getYear() > TOP_EDGE_YEAR)
                        userInputDateTime = userInputDateTime.minusYears(YEARS_IN_ONE_CENTURY);

                    LocalDate finalUserInputDateTime = userInputDateTime;
                    companyList.companies.stream()
                            .filter(company -> DateTimeUtil.getDateFromStringFormat(company.founded,JSON_DATE_FORMAT)
                                    .isAfter(finalUserInputDateTime))
                            .forEach(company -> System.out.format("%s - Дата основания %s\n",company.name,company.founded));
                    break;

                case "2":
                    System.out.print("\nВведите наименование валюты: ");
                    for (var currency : CURRENCY_NAMES)
                        System.out.print(currency + ", ");
                    System.out.println();

                    boolean isCurrencyNameCorrect = false;
                    do {
                        userInput = scanner.nextLine();
                        for (var currencyName : CURRENCY_NAMES)
                        {
                            if (currencyName.equals(userInput))
                            {
                                isCurrencyNameCorrect = true;
                                break;
                            }
                        }

                        if (!isCurrencyNameCorrect)
                            System.out.println("Введеная строка не является валютой");

                    }while (!isCurrencyNameCorrect);


                    String finalUserInput = userInput;
                    companyList.companies
                            .forEach(company -> company.securities.stream()
                                    .filter(security->
                                            security.currency.stream()
                                                    .anyMatch(currency->currency.equals(finalUserInput)))
                                    .forEach(security -> {
                                        System.out.format("\nЦенная бумага в валюте %s:\n",finalUserInput);
                                        System.out.format("\tКод: %s\n\tВладелец: %s\n",
                                                security.code,company.name);
                                    }));

                    break;
            }

        }while(!userInput.equals("9"));
    }
}
