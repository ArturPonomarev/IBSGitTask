import SweetThings.*;

public class Main {
    public static void main(String[] args)
    {
        SweetBox box = new SweetBox();

        box.AddSweet(new Candy());
        box.AddSweet(new Chocolate());
        box.AddSweet(new Marmalade());

        box.PrintPrice();
        box.PrintWeight();
        box.PrintAllSweets();
    }
}