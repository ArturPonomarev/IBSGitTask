public class Candy extends BaseSweetThing{

    private static final double MIN_RADIUS = 1.f;
    private static final double MAX_RADIUS = 10.f;

    private double radius;

    public Candy() {
        name = "Candy";
        price = 25.f;
        weight = 50.f;

        radius = Math.random() * (MAX_RADIUS - MIN_RADIUS) + MIN_RADIUS;
    }

    public double GetRadius() {return radius;}
}
