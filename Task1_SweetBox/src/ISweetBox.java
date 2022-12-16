import SweetThings.BaseSweetThing;

public interface ISweetBox {
    public int AddSweet(BaseSweetThing sweetThing);
    public void DeleteSweetLast();
    public void DeleteSweetByIndex(int index);
    public void PrintWeight();
    public void PrintPrice();
    public void PrintAllSweets();
    public void OptimizeWeight();
    public void OptimizePrice();
}
