import java.util.*;

public class ProductBase implements IProductBase {
    private Map<Integer, Product> products = new HashMap<>();
    private Map<Integer, QuantityOfProduct> productQuantity = new HashMap<>();

    @Override
    public void addProduct(int id, String productName, Manufacturer manufacturer, double count, Unit unit,
                           double price, Category category, int weight) throws IllegalArgumentException {
        Product product = new Product(id, productName, manufacturer, count, unit, price, category, weight);
        if (products.containsKey(id)) {
            throw new IllegalArgumentException("Товар с ID " + id + " не добавлен, т.к. товар с таким ID уже существует. \n" +
                                               product.getProductDescription());
        }
        products.put(id, product);
        productQuantity.put(id, null);  //на случай, если случайно будет реализована проверка id через обращение к productQuantity
    }

    @Override
    public void addProductQuantity(int id, double quantity, Unit unit) {
        checkID(id);
        if (productQuantity.get(id) == null) {
            productQuantity.put(id, new QuantityOfProduct(quantity, unit));
        } else {
            try {
                productQuantity.get(id).addQuantity(quantity, unit);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e.getMessage() + " ID товара " + id);
            }
        }
    }

    @Override
    public QuantityOfProduct getQuantityOfProduct(int id) throws IllegalArgumentException{
        checkID(id);
        return !(productQuantity.get(id) == null) ? productQuantity.get(id) : new QuantityOfProduct(0d, null);
    }

    @Override
    public Product getProductByID(int id) throws IllegalArgumentException {
        checkID(id);
        return products.get(id);
    }

    @Override
    public List<Product> getOrderedProductList() {
        TreeSet<Product> productSet = new TreeSet<>(new PriceComparatorByIncrease());
        productSet.addAll(products.values());
        return new ArrayList<>(productSet);
    }

    void checkID(int ID) throws IllegalArgumentException {
        if (products.get(ID) == null) {
            throw new IllegalArgumentException("Товар с ID " + ID + " отсутствует");
        }
    }
}