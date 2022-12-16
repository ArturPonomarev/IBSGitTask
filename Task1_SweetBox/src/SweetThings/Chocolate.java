package SweetThings;

import SweetThings.BaseSweetThing;

public class Chocolate extends BaseSweetThing {

    private double cacaoPercent;

    public Chocolate()
    {
        name = "Chocolate";
        price = 150;
        weight = 200;

        cacaoPercent = Math.random();
    }
}
