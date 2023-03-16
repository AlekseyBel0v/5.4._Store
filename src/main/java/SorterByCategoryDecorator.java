import java.util.List;
import java.util.Scanner;

public class SorterByCategoryDecorator implements Sortable {
    private Category category;
    private List<Product> products;

    SorterByCategoryDecorator(Sortable sortedProductBase) {
        products = sortedProductBase.getOrderedProductList();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите категорию товара и введите ее название");
        System.out.println(Category.getRoot().getTreeToString());
        this.category = Category.getRoot().searchCategoryByName(scanner.nextLine());
    }

    @Override
    public List<Product> getOrderedProductList() {
                for (int i = products.size() - 1; i > - 1; i--) {
                    if (!products.get(i).getListCategories().contains(category)) {
                        products.remove(i);
                }
        }
        return products;
    }
}