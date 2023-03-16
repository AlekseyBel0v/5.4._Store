public interface IQuantityOfProduct {
    Unit getUnit();

    @Override
    String toString();

    /**
     Метод бросает IllegalArgumentException, если новые ед. измерения не совпадают со старыми.
     */
    void addQuantity(double quantity, Unit unit) throws IllegalArgumentException;

    Double getQuantityOfProduct();
}
