import java.util.Comparator;

public class PriceComparatorByIncrease implements Comparator<Product> {
    @Override
    public int compare(Product product1, Product product2) {
        return Double.compare(product1.getApproximatePrice(), product2.getApproximatePrice());
    }
}