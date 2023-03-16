public interface IBuyer {
    void putProductToBasket();

    void buy();

    String seeBasket();

    void seeTracker(int userID);
}