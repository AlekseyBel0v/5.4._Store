public class QuantityOfProduct implements IQuantityOfProduct {
    private Double quantityOfProduct;
    private Unit unit;

    public QuantityOfProduct(Double quantityOfProduct, Unit unit) {
        this.quantityOfProduct = quantityOfProduct;
        this.unit = unit;
    }

    @Override
    public void addQuantity(double quantity, Unit unit) throws IllegalArgumentException {
        if (!(this.unit == unit)){
            throw new IllegalArgumentException("уже установленное (" + this.unit.dimencion + ") и заданное (" + unit.dimencion + ") значения единиц измерения не совпадают! Количество товара не изменилось.");
        }
        this.quantityOfProduct += quantity;
    }

    @Override
    public Unit getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return unit == null ? "товар отсутствует" : quantityOfProduct + " " + unit.dimencion;
    }

    public Double getQuantityOfProduct() {
        return quantityOfProduct;
    }
}
