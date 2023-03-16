import java.util.*;

public class Buyer extends User implements IBuyer {
    static HashMap<Integer, Buyer> buyers = new HashMap<>();

    private List<PositionOfBasket> basket = new ArrayList<>();        //корзина покупателя
    private PaymentStatus paymentStatus;                              //статус оплаты
    private Payment payment;                                          //способ оплаты
    private ITracker tracker = new Tracker(this);                //трекер заказов

    Buyer(Store store) {
        super(store);
        buyers.put(getID(), this);
    }

    @Override
    public void putProductToBasket() {
        int i = 1;
        System.out.println("\n" +
                           i++ + ". Чтобы добавить товар в корзину введите через запятую ID товара, количество и нажмите Enter.\n" +
                           "Например: 44, 4\n" +
                           "где 44 - это ID, 4 - количество.\n" +   //для упрощения количество товара является целым числом
                           i++ + ". Чтобы добавить еще товар, повторите действие выше.\n" +
                           i++ + ". Чтобы посмотреть корзину, нажмите Enter\n" +
                           i++ + ". Чтобы перейти в начальное меню для оформелния заказа, введите \"Выход\"\n");
        String input = "";
        while (!input.equals("выход")) {
            input = new Scanner(System.in).nextLine().toLowerCase();
            if (input.equals("")) {
                System.out.println(this.seeBasket());
            } else if (!input.equals("выход")) {
                Object[] param = Arrays.stream(input.toLowerCase().trim().split(",")).toArray();
                int productID = Integer.parseInt((String) param[0]);
                double quantity = Double.parseDouble((String) param[1]);
                try {
                    basket.add(new PositionOfBasket(getID(), getStore().getProductByID(productID).getKey(), quantity, getStore().getProductByID(productID).getValue()));
                    System.out.println("В корзину добавлен товар c ID " + productID);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage() + "Товар не добален в корзину");
                }
            }
        }
    }

    // TODO: 13.03.2023
    //  Условно принято, что количество товара в базе достаточное для совершения покупки

    // TODO: 14.03.2023
    //  При покупке количество товаров в магазине не уменьшается

    @Override
    public void buy() {
        if (basket.size() == 0) {
            System.out.print("\nПустая корзина. Чтобы оформить заказ, выберите товары.\n");
            return;
        }
        paymentStatus = PaymentStatus.UNPAID;
        // TODO: 14.03.2023 должны быть ненулевые значения
        initName();
        initEMail();
        tracker.setAddress();
        Integer input = 0;
        do {
            try {
                choosePaymentAndPay();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.print("Выберите действие:\n" +
                                 "1. Попробовать еще раз\n" +
                                 "2. Выйти в начальное меню\n" +
                                 "Для выбора действия введите номер: ");
                input = new Scanner(System.in).nextInt();      // либо 1 - повторить оплату (команды 2, 3), либо 2 - выход в начальное меню
            }
        } while (paymentStatus == PaymentStatus.UNPAID && input == 1);
        if (paymentStatus == PaymentStatus.PAID) {                       // Добавление заказа в трекер, очистка корзины
            tracker.addOrder(basket);
            basket = new ArrayList<>();
        }
    }

    void choosePaymentAndPay()  throws Exception {
        System.out.println("\nВыбирите способ оплаты:");
        int j = 1;
        for (PaymentType pt : PaymentType.values()) {
            System.out.println("   " + j++ + " - " + pt.typeName);
        }
        System.out.print("Для выбора способа оплаты введите номер и нажмите Enter: ");
        Integer input = new Scanner(System.in).nextInt();
        switch (input) {
            case 1:
                payment = new BankCard();
                payment.validate();
                paymentStatus = payment.pay(this.getSumCostOfBasket());  //если оплачено, то статус "оплачено"
                break;
        }
    }

    @Override
    public String seeBasket() {
        StringBuilder description = new StringBuilder("Выбраны следующие товары:\n");
        int i = 1;
        for (PositionOfBasket p : basket) {
            description.append(i++).append(". ").append(p.toString()).append('\n');
        }
        double cost = getSumCostOfBasket();
        description.append("Суммарная стоимость корзины составляет : ").append(cost);
        return description.toString();
    }

    public double getSumCostOfBasket() {
        double cost = 0;
        for (PositionOfBasket p : basket) {
            cost += p.getCost();
        }
        return cost;
    }

    @Override
    public void seeTracker(int userID) {
        tracker.seeOrdersInProcess();
    }
}

enum PaymentStatus {
    PAID,
    UNPAID
}