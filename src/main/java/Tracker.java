import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tracker implements ITracker {
    List<Order> orderHistoryList = new ArrayList<>();       //история заказов
    List<Status> orderStatusList = new ArrayList<>();       //порядок в списке "статус заказа" соответствует порядку истории заказов
    User user;
    String address;

    public Tracker(User user) {
        this.user = user;
    }

    @Override
    public void seeOrdersInProcess() {
        System.out.println("СПИСОК ЗАКАЗОВ, которые находятся в работе: ");
        for (int i = 0; i < orderStatusList.size(); i++) {
            if (orderStatusList.get(i) == Status.IN_PROCESS) {
                System.out.println(seeOrder(orderHistoryList.get(i)));
            }
        }
    }

    String seeOrder(Order order) {
        double cost = 0;
        StringBuilder description = new StringBuilder("\nСостав заказа:\n");
        int i = 1;
        for (PositionOfBasket o : order.getBasket()) {
            description.append(i++).append(". ").append(o.toString()).append('\n');
            cost += o.getCost();
        }
        description.append("Суммарная стоимость корзины составляет : ").append(cost)
                .append("\nАдрес доставки: ").append(order.getAddress())
                .append("\nЗаказчик: ").append(user.getUserName())
                .append("\nНомер заказа: ").append(user.getID())
                .append("\nДата заказа: ").append(order.getDateOfCreating());
        return description.toString();
    }

    // TODO: 16.03.2023 Логику необходимо вынести в дальнейшем в отдельный класс Почты
    void sendOrderTrackerToEMail() {
        System.out.println("\nНа електонный адрес " + user.getUserEmail() + " отправлен трекер заказа." +
                           "\nНомер вашего заказа " + user.getID() + ". По данному номеру вы можете получить " +
                           "\nдетали заказа в начальном меню: \"5. Посмотреть трекер\"");
    }

    @Override
    public void addOrder(List<PositionOfBasket> basket) {
        Order newOrder = new Order(basket, address);
        orderHistoryList.add(newOrder);
        orderStatusList.add(Status.IN_PROCESS);
        System.out.println("\nЗаказ оформлен и принят в работу!");
        sendOrderTrackerToEMail();
        System.out.println(seeOrder(newOrder));
    }

    @Override
    public void setAddress() {
        System.out.print("\nВведите адрес доставки: ");
        address = new Scanner(System.in).nextLine();
    }
}

enum Status {
    COMPLETE,
    IN_PROCESS
}