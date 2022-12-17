package SweetThings;

public class Chocolate extends BaseSweetThing {

    private double cacaoPercent;

    public Chocolate()
    {
        name = "Шоколад";
        price = 150;
        weight = 200;

        cacaoPercent = Math.random();
    }
}
