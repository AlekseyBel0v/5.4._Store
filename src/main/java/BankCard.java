import java.util.Scanner;

public class BankCard implements Payment {
    private long cardNumber;
    private int CVC;
    private int month;
    private int year;

    public BankCard() throws Exception {
        // TODO: 13.03.2023 проверка ввода
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nДля оплаты банковской картой заполните следующие поля: ");
        System.out.print("\nВведите номер карты: ");
        this.cardNumber = scanner.nextLong();
        System.out.print("\nВведите код СVC: ");
        this.CVC = scanner.nextInt();
        System.out.print("\nВведите срок годности - месяц: ");
        this.month = scanner.nextInt();
        System.out.print("\nВведите срок годности - год: ");
        this.year = scanner.nextInt();
        validate();
    }

    @Override
    public void validate() throws Exception {
        //throw new Exception("\nКарта с номером " + cardNumber + " не заригестрирована.");
    }

    @Override
    public PaymentStatus pay(double cost) throws Exception {
        //throw new Exception("Оплата не прошла.");
        System.out.println("\nОплата заказа на сумму " + cost + " прошла успешно!");
        return PaymentStatus.PAID;
    }
}
