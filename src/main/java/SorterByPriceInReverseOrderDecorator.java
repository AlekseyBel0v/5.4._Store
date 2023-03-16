import java.util.List;

public class SorterByPriceInReverseOrderDecorator implements Sortable {
    private Sortable productBase;

    public SorterByPriceInReverseOrderDecorator(Sortable productBase) {
        this.productBase = productBase;
    }

    @Override
    public List<Product> getOrderedProductList() {
        List<Product> productList = productBase.getOrderedProductList();
        productList.sort(new PriceComparatorByIncrease().reversed());
        return productList;
    }
}
