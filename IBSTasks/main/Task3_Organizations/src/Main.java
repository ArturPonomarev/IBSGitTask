package src;

import src.JsonModels.CompanyList;
import src.Utils.DateTimeUtil;
import src.Utils.JsonUtil;
import src.Utils.Printer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.function.UnaryOperator;

public class Main {

    private static final String COMPANIES_JSON_PATH = "./IBSTasks/main/resources/Organizations.json";
    private static final String SECURITY_DATE_FORMAT = "dd.MM.yyyy";

    public static void main(String[] args) throws IOException {

        CompanyList companyList = JsonUtil.DeserializeFile(COMPANIES_JSON_PATH,CompanyList.class);


        int lostSecureSum = 0;

        companyList.companies
                .forEach(company -> {
                    Printer.PrintCompanyInformation(company);
                    company.securities.stream()
                            .filter(security -> DateTimeUtil.ConvertToNeedDateTime(security.date,SECURITY_DATE_FORMAT)
                                    .isBefore(LocalDate.now())
                            )
                            .forEach(Printer::PrintSecurityInformation);
                });


        System.out.println("Просроченных бумаг: " + lostSecureSum);

        LocalDate date = LocalDate.now();
    }
}
