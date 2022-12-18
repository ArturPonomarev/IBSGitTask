package SweetThings;

public class Candy extends BaseSweetThing {

    private static final double MIN_RADIUS = 1.f;
    private static final double MAX_RADIUS = 10.f;

    private double radius;

    public Candy() {
        super.name = "Конфета";
        super.price = 25;
        super.weight = 50;

        radius = Math.random() * (MAX_RADIUS - MIN_RADIUS) + MIN_RADIUS;
    }

    public double GetRadius() {return radius;}
}
