import java.util.List;

public class SorterByTheProductIS implements Sortable{
    Sortable productBase;
    ProductBase productAndQuantityBase;

    public SorterByTheProductIS(Sortable productBase, ProductBase productAndQuantityBase) {
        this.productBase = productBase;
        this.productAndQuantityBase = productAndQuantityBase;
    }

    @Override
    public List<Product> getOrderedProductList() {
        List<Product> productList = productBase.getOrderedProductList();
        productList = productList.stream().filter(x->{
            int id = x.getCharacteristicValue(ProductCharacteristic.ID);
            return productAndQuantityBase.getQuantityOfProduct(id).getQuantityOfProduct() > 0;
        }).toList();
        return productList;
    }
}
