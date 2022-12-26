package src;

import src.JsonModels.CompanyList;
import src.Utils.DateTimeUtil;
import src.Utils.JsonUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Main {
    private final static int TOP_EDGE_YEAR = 2070;
    private final static int YEARS_IN_ONE_CENTURY = 100;
    private final static String COMPANIES_JSON_PATH = "./IBSTasks/main/resources/Organizations.json";
    private final static String JSON_DATE_FORMAT = "d.MM.yyyy";
    private final static String COMPANY_PRINT_DATE_FORMAT = "dd/MM/yy";
    private final static String INCORRECT_USER_FORMAT = "";
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

        AtomicInteger oldSecuritySum = new AtomicInteger();
        companyList.companies
                .forEach(company -> company.securities.stream()
                        .filter(security -> DateTimeUtil.getDateFromStringFormat(security.date,JSON_DATE_FORMAT).isBefore(LocalDate.now()))
                        .forEach(security -> {
                            System.out.println("\nЦенная бумага:");
                            System.out.format("\tКод: %s\n\tДата истечения: %s\n\tВладелец: %s\n",
                                    security.code,security.date,company.name);
                            oldSecuritySum.getAndIncrement();
                        }));
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
                    PrintCompaniesAfterInputDate(companyList);
                    break;

                case "2":
                    PrintSecuritiesWithInputCurrency(companyList);
                    break;
            }

        }while(!userInput.equals("9"));
    }

    private static void PrintCompaniesAfterInputDate(CompanyList companyList)
    {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        System.out.format("Введите дату в одном из следующих форматов: %s; %s; %s; %s;\n",
                NEED_USER_INPUT_FORMATS[0], NEED_USER_INPUT_FORMATS[1], NEED_USER_INPUT_FORMATS[2], NEED_USER_INPUT_FORMATS[3]);

        String userFormat;
        do{
            userInput = scanner.nextLine();
            String finalUserInput = userInput;

            userFormat = Stream.of(NEED_USER_INPUT_FORMATS)
                    .filter(format -> DateTimeUtil.CheckDateFormatIsCorrect(finalUserInput,format))
                    .findFirst().orElse(INCORRECT_USER_FORMAT);

            System.out.println("Введеный формат не корректен, повторите ввод.");
        }while (userFormat.equals(INCORRECT_USER_FORMAT));


        LocalDate userInputDateTime = DateTimeUtil.getDateFromStringFormat(userInput, userFormat);
        if (userInputDateTime.getYear() > TOP_EDGE_YEAR)
            userInputDateTime = userInputDateTime.minusYears(YEARS_IN_ONE_CENTURY);

        LocalDate finalUserInputDateTime = userInputDateTime;
        companyList.companies.stream()
                .filter(company -> DateTimeUtil.getDateFromStringFormat(company.founded,JSON_DATE_FORMAT)
                        .isAfter(finalUserInputDateTime))
                .forEach(company -> System.out.format("%s - Дата основания %s\n",company.name,company.founded));
    }

    private static void PrintSecuritiesWithInputCurrency(CompanyList companyList)
    {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        System.out.format("\nВведите наименование валюты (RUB, EU, USD)\n");

        boolean isCurrencyNameCorrect;
        do {
            userInput = scanner.nextLine();
            isCurrencyNameCorrect = List.of(CURRENCY_NAMES).contains(userInput);

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
    }
}
