import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Order {        //  Заказ
    private List<PositionOfBasket> basket;
    private String address;
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    Date dateOfCreating;

    public Order(List<PositionOfBasket> basket, String address) {
        this.basket = basket;
        this.address = address;
        this.dateOfCreating = new Date();
    }

    public List<PositionOfBasket> getBasket() {
        return basket;
    }

    public String getAddress() {
        return address;
    }

    public String getDateOfCreating() {
        return formatter.format(dateOfCreating);
    }
}


