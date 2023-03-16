import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SorterDecorator implements Sortable {
    private Sortable productBase;
    private ProductBase productAndQuantityBase; //дополняет productBase по количеству товаров
    private List<Product> productList;

    public SorterDecorator(ProductBase productBase) {   //ProductBase имплементирует IProductBase, который наследует Sortable
        this.productBase = productBase;
        this.productAndQuantityBase = productBase;
        productList = productBase.getOrderedProductList();
    }

    @Override
    public List<Product> getOrderedProductList() {
        System.out.println("Выберите команды для сортировки списка продуктов\n" +
                           "1. Сортировать список по увеличению цены\n" +
                           "2. Сортировать список по уменьшению цены\n" +
                           "3. Отфильтровать список по категории товаров\n" +
                           "4. Отфильтровать список по наличию товара\n" +
                           "В дальнейшем можно реализовать различные фильтры по карактеристикам\n" +
                           "Для использования команд сортирвки, введите их номера через запятую");
        List<Integer> msg = Arrays.stream(new Scanner(System.in).nextLine().trim().split(","))
                .map(Integer::parseInt).toList();
        if (msg.contains(2)) {
            productBase = new SorterByPriceInReverseOrderDecorator(productBase);
            productList = productBase.getOrderedProductList();
        }
        if (msg.contains(3)) {
            productBase = new SorterByCategoryDecorator(productBase);
            productList = productBase.getOrderedProductList();
        }
        if (msg.contains(4)) {
            productBase = new SorterByTheProductIS(productBase, productAndQuantityBase);
            productList = productBase.getOrderedProductList();
        }
        return productList;
    }
}