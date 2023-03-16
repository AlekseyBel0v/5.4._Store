public class PositionOfBasket {     //одна позиция в корзине товаров
    private int productID;
    private Product product;
    private double quantity;
    private Unit unit;
    private double cost;

    public PositionOfBasket(int productID, Product product, double quantity, Unit unit) {
        this.productID = productID;
        this.product = product;
        this.quantity = quantity;
        this.unit = unit;
        this.cost = (double) product.getCharacteristicValue(ProductCharacteristic.PRICE) * quantity;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "ID=" + productID +
               ",   название = " + product.getCharacteristicValue(ProductCharacteristic.PRODUCT_NAME) + ",\n" +
               "количество = " + quantity + " " + unit.dimencion +
               ",   сум.стоимость = " + cost;
    }
}