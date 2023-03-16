import java.util.List;

public interface ITracker {
    void seeOrdersInProcess();

    void addOrder(List<PositionOfBasket> basket);

    void setAddress();
}