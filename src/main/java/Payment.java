public interface Payment {
    void validate() throws Exception;

    PaymentStatus pay(double cost) throws Exception;
}
