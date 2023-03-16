public interface IProductBase extends Sortable{

    /**
     Метод бросает IllegalArgumentException, если в базе хранится товар с таким ID.
     */
    void addProduct(int id, String productName, Manufacturer manufacturer, double count, Unit unit, double price,
                    Category category, int weight) throws IllegalArgumentException;

    /**
     Метод бросает IllegalArgumentException, если в базе отсутствует товар с таким ID, или если новые ед. измерения не
     совпадают со старыми.
     */
    void addProductQuantity(int id, double quantity, Unit unit) throws IllegalArgumentException;

    /**
     * @param id
     * @return
     * @throws IllegalArgumentException если товар с id отсутствует
     */
    QuantityOfProduct getQuantityOfProduct(int id) throws IllegalArgumentException;

    /**
     * @param id
     * @return
     * @throws IllegalArgumentException если товар с id отсутствует
     */
    Product getProductByID(int id) throws IllegalArgumentException;

    @Override
    String toString();
}
