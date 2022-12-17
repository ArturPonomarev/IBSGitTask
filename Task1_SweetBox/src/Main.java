import SweetThings.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UiStates currentState = UiStates.BOX_INTERFACE;
        SweetBox box = new SweetBox();
        Scanner in = new Scanner(System.in);

        while (currentState != UiStates.EXIT)
        {
            switch (currentState)
            {
                case BOX_INTERFACE:
                    System.out.println("\n\n");
                    box.PrintWeight();
                    box.PrintPrice();
                    box.PrintAllSweets();
                    System.out.println("\nВведите цифру для выполнения операции:");
                    System.out.println("1 - добавить конфету;");
                    System.out.println("2 - добавить шоколад;");
                    System.out.println("3 - добавить мармелад;");
                    System.out.println("4 - убрать последнюю сладость;");
                    System.out.println("5 - убрать сладость по номеру;");
                    System.out.println("6 - оптимизировать по весу;");
                    System.out.println("7 - оптимизировать по цене;");
                    System.out.println("9 - закрыть программу;");

                    int userInput = in.nextInt();

                    switch (userInput)
                    {
                        case 1:
                            box.AddSweet(new Candy());
                            break;
                        case 2:
                            box.AddSweet(new Chocolate());
                            break;
                        case 3:
                            box.AddSweet(new Marmalade());
                            break;
                        case 4:
                            box.DeleteSweetLast();
                            break;
                        case 5:
                            currentState = UiStates.DELETE_SWEET_BY_INDEX;
                            break;
                        case 6:
                            currentState = UiStates.OPTIMIZE_WEIGHT;
                            break;
                        case 7:
                            currentState = UiStates.OPTIMIZE_PRICE;
                            break;
                        case 9:
                            currentState = UiStates.EXIT;
                            break;
                    }
                    break;

                case DELETE_SWEET_BY_INDEX:
                    System.out.println("\nВведите номер удаляемой сладости.");
                    box.DeleteSweetByIndex(in.nextInt() - 1);
                    currentState = UiStates.BOX_INTERFACE;
                    break;

                case OPTIMIZE_WEIGHT:
                    System.out.println("\nВведите желаемый вес коробки.");
                    box.OptimizeWeight(in.nextInt());
                    currentState = UiStates.BOX_INTERFACE;
                    break;

                case OPTIMIZE_PRICE:
                    System.out.println("\nВведите желаемую стоимость коробки.");
                    box.OptimizePrice(in.nextInt());
                    currentState = UiStates.BOX_INTERFACE;
                    break;
            }
        }
    }
}