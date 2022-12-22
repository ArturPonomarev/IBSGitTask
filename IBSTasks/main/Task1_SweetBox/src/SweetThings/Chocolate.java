package Task1_SweetBox.src.SweetThings;

public class Chocolate extends BaseSweetThing {

    private double cacaoPercent;

    public Chocolate()
    {
        super.name = "Шоколад";
        super.price = 150;
        super.weight = 200;

        cacaoPercent = Math.random();
    }
}
