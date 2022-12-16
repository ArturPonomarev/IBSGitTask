package SweetThings;

import SweetThings.BaseSweetThing;

import java.util.Random;

public class Marmalade extends BaseSweetThing {

    private static final String[] SHAPES = {"Sphere","Triangle","Square"};

    private String shape;

    public Marmalade()
    {
        name = "Marmalade";
        price = 300;
        weight = 75;

        Random random = new Random();
        shape = SHAPES[random.nextInt(SHAPES.length)];
    }
}
