import java.util.*;

public class Product implements IProduct{
    private Map<ProductCharacteristic, Object> productCharacteristics = new HashMap<>();

    Product(int id, String productName, Manufacturer manufacturer, double count, Unit unit, double price, Category category, double weight) {
        productCharacteristics.put(ProductCharacteristic.ID, id);
        productCharacteristics.put(ProductCharacteristic.PRODUCT_NAME, productName);
        productCharacteristics.put(ProductCharacteristic.MANUFACTURER, manufacturer);
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        productCharacteristics.put(ProductCharacteristic.ALL_CATEGORIES, categories);
        productCharacteristics.put(ProductCharacteristic.COUNT, count);
        productCharacteristics.put(ProductCharacteristic.UNIT, unit);
        productCharacteristics.put(ProductCharacteristic.PRICE, price);
        productCharacteristics.put(ProductCharacteristic.WEIGHT, weight);
    }

        public Product addCharacteristic(ProductCharacteristic productCharacteristic, Object characteristicValue) {
        if (productCharacteristics.containsKey(productCharacteristic)) {
            System.out.println("Характеристика продукта " + productCharacteristic.characteristicName + "не добавлена, т.к. она уже существует");
        }
        this.productCharacteristics.put(productCharacteristic, characteristicValue);
        return this;
    }

    //Метод сначала выводит обязательные характеристики. Потом идут остальные характеристики в случайном порядке.
    public List<ProductCharacteristic> getListCharacteristics() {
        List<ProductCharacteristic> listCharacteristics = new ArrayList<>(List.of(ProductCharacteristic.ID, ProductCharacteristic.PRODUCT_NAME,
                ProductCharacteristic.MANUFACTURER, ProductCharacteristic.ALL_CATEGORIES, ProductCharacteristic.COUNT,
                ProductCharacteristic.UNIT, ProductCharacteristic.PRICE));
        for (ProductCharacteristic characteristic : productCharacteristics.keySet()) {
            if (!listCharacteristics.contains(characteristic)) {
                listCharacteristics.add(characteristic);
            }
        }
        return listCharacteristics;
    }

    @Override
    public <E> E getCharacteristicValue(ProductCharacteristic productCharacteristic) {
        if (this.getListCharacteristics().contains(productCharacteristic)) {
            return (E) productCharacteristics.get(productCharacteristic);
        } else return null;
    }

    @Override
    public List<Category> getListCategories(){
        return (List<Category>) productCharacteristics.get(ProductCharacteristic.ALL_CATEGORIES);
    }

    @Override
    public String getProductDescription() {
        StringBuilder productDescription = new StringBuilder("Описание товара:\n");
        for (ProductCharacteristic characteristic : this.getListCharacteristics()) {
            productDescription.append(characteristic.characteristicName).append(": ")
                    .append(productCharacteristics.get(characteristic)).append('\n');
        }
        return productDescription.toString();
    }

    @Override
    public double getApproximatePrice() throws RuntimeException {
        double ApproximatePrice = (double) productCharacteristics.get(ProductCharacteristic.PRICE)
                           / (double) productCharacteristics.get(ProductCharacteristic.WEIGHT)
                           * 1000;
        if (ApproximatePrice == 0) {
            throw new RuntimeException("Стоимость товара заполнена неправильно!\n" + this.getProductDescription());
        }
        return ApproximatePrice;
    }
}