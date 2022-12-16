import SweetThings.BaseSweetThing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SweetBox implements ISweetBox {

    private ArrayList<BaseSweetThing> sweetsArray;

    public SweetBox()
    {
        sweetsArray = new ArrayList<BaseSweetThing>();
    }

    @Override
    public int AddSweet(BaseSweetThing sweetThing) {
        sweetsArray.add(sweetThing);
        return sweetsArray.indexOf(sweetThing);
    }

    @Override
    public void DeleteSweetLast() {
        sweetsArray.remove(sweetsArray.size()-1);
    }

    @Override
    public void DeleteSweetByIndex(int index) {
        sweetsArray.remove(index);
    }

    @Override
    public void PrintWeight() {
        System.out.println("Общий вес коробки: " + CalculateWeight());
    }

    @Override
    public void PrintPrice() {
        System.out.println("Общая стоимость коробки: " + CalculatePrice());
    }

    @Override
    public void PrintAllSweets() {
        System.out.println("Все сладости в коробке");
        System.out.format("%3s%15s%6s%6s\n","№","Название","Вес","Цена");
        for (int i = 0; i<sweetsArray.size(); ++i)
        {
            System.out.format("%3d%15s%6d%6d\n",
                    (i+1),
                    sweetsArray.get(i).GetName(),
                    sweetsArray.get(i).GetWeight(),
                    sweetsArray.get(i).GetPrice());
        }
    }

    @Override
    public void OptimizeWeight(int needWeight) {
        Collections.sort(sweetsArray, new Comparator<BaseSweetThing>() {
            @Override
            public int compare(BaseSweetThing o1, BaseSweetThing o2) {
                return o2.GetWeight() - o1.GetWeight();
            }
        });

        while (CalculateWeight()>needWeight)
        {
            DeleteSweetLast();
        }
    }

    @Override
    public void OptimizePrice(int needPrice) {
        Collections.sort(sweetsArray, new Comparator<BaseSweetThing>() {
            @Override
            public int compare(BaseSweetThing o1, BaseSweetThing o2) {
                return o2.GetPrice() - o1.GetPrice();
            }
        });

        while (CalculatePrice()>needPrice)
        {
            DeleteSweetLast();
        }
    }

    private int CalculateWeight()
    {
        int weightSum = 0;
        for (var sweet : sweetsArray)
        {
            weightSum+=sweet.GetWeight();
        }
        return weightSum;
    }

    private int CalculatePrice()
    {
        int priceSum = 0;
        for (var sweet : sweetsArray)
        {
            priceSum+=sweet.GetPrice();
        }
        return priceSum;
    }
}
