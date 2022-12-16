import SweetThings.BaseSweetThing;

import java.util.ArrayList;

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
        double weightSum = 0.d;
        for (var sweet : sweetsArray)
        {
            weightSum+=sweet.GetWeight();
        }
        System.out.println("Общий вес коробки: " + weightSum);
    }

    @Override
    public void PrintPrice() {
        double priceSum = 0.d;
        for (var sweet : sweetsArray)
        {
            priceSum+=sweet.GetPrice();
        }
        System.out.println("Общая стоимость коробки: " + priceSum);
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
    public void OptimizeWeight() {

    }

    @Override
    public void OptimizePrice() {

    }
}
