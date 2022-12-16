public class Chocolate extends BaseSweetThing {

    private double cacaoPercent;

    public Chocolate()
    {
        name = "Chocolate";
        price = 150.d;
        weight = 200.d;

        cacaoPercent = Math.random();
    }
}
