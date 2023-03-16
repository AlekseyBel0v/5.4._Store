import java.util.*;

public class Store {
    private IManufacturerBase manufacturerBase = new ManufacturerBase();    //база производителей товаров
    private ProductBase productBase = new ProductBase();                    //база, в которой хранится товар и его количество

    public Store() {
        //Создание дерева категорий
        Category milkProductsEgs = new Category("Молочные продукты, яйцо", Category.getRoot());
        Category kefirAndDairyProducts = new Category("кефир и кисломолочные продукты", Category.getRoot());
        Category milkCream = new Category("Moloko, сливки", milkProductsEgs);

        //Наполснение базы произовдителей товаров
        manufacturerBase.addManufacturer("ВКУССВИЛЛ", Country.RUSSIA)
                .addManufacturer("НИКОН ОООмолоко", Country.RUSSIA)
                .addManufacturer("Савушкин продукт", Country.BELARUS);

        //Наполнение базы товаров
        productBase.addProduct(1, "Молоко 2,5% в бутылке, 900 мл", manufacturerBase.getManufacturer("Савушкин продукт"),
                0.9, Unit.L, 100, milkCream, 900);
        productBase.addProduct(2, "Молоко 3,5% в бутылке, 900 мл", manufacturerBase.getManufacturer("НИКОН ОООмолоко"),
                0.9, Unit.L, 130, milkCream, 900);
        productBase.addProduct(3, "Молоко 3,5% в бутылке, 1500 мл", manufacturerBase.getManufacturer("Савушкин продукт"),
                1.5, Unit.L, 170, milkCream, 1500);
        productBase.addProduct(4, "Молоко 3,5% в бутылке, 1500 мл", manufacturerBase.getManufacturer("ВКУССВИЛЛ"),
                1.5, Unit.L, 180, milkCream, 1500);
        productBase.addProduct(5, "Айран 2%, 400 мл", manufacturerBase.getManufacturer("НИКОН ОООмолоко"),
                400, Unit.G, 70, kefirAndDairyProducts, 400);
        productBase.addProduct(6, "Ацидофилин 3,2%, 200 мл", manufacturerBase.getManufacturer("ВКУССВИЛЛ"),
                200, Unit.G, 70, kefirAndDairyProducts, 200);
        productBase.addProduct(7, "Биойогурт детский с вишней 2,5%, 210 мл", manufacturerBase.getManufacturer("ВКУССВИЛЛ"),
                210, Unit.G, 50, kefirAndDairyProducts, 210);
        productBase.addProductQuantity(1, 100, Unit.PCS);
        productBase.addProductQuantity(2, 100, Unit.PCS);
        productBase.addProductQuantity(3, 100, Unit.PCS);
        productBase.addProductQuantity(4, 100, Unit.PCS);
        productBase.addProductQuantity(5, 100, Unit.PCS);
        productBase.addProductQuantity(6, 100, Unit.PCS);
        productBase.addProductQuantity(7, 100, Unit.PCS);
    }

    public AbstractMap.SimpleEntry<Product, Unit> getProductByID(int productID) throws IllegalArgumentException{
        return new AbstractMap.SimpleEntry<>(productBase.getProductByID(productID), productBase.getQuantityOfProduct(productID).getUnit());
    }

    public void getProductList() {
        for (Product p : new SorterDecorator(productBase).getOrderedProductList()) {
            int id = p.getCharacteristicValue(ProductCharacteristic.ID);
            System.out.print(p.getProductDescription() +
                             "В наличии: " + productBase.getQuantityOfProduct(id).toString() +
                             "\nЦена р\\г товара (ДЛЯ ТЕСТОВ): " + p.getApproximatePrice() +
                             "\n___________________________________________________\n");
        }
    }

    public void startShopping() {
        Buyer newBuyer = new Buyer(this);
        Scanner scanner = new Scanner(System.in);
        int msg = 0;
        System.out.println("Здравствуйте!");
        while (!(msg == 6)) {
            System.out.print("Выберите действие:\n" +
                             "1. Перейти к списку товаров\n" +
                             "2. Добавить товары с номерами ID в корзину\n" +
                             "3. Перейти в корзину\n" +
                             "4. Оформить заказ\n" +
                             "5. Посмотреть трекер заказов\n" +
                             "6. Выйти из программы\n" +
                             "Чтобы выбрать команду, введите ее номер: ");

            msg = scanner.nextInt();
            switch (msg) {
                case 1:
                    getProductList();
                    break;
                case 2:
                    newBuyer.putProductToBasket();
                    break;
                case 3:
                    System.out.println(newBuyer.seeBasket());
                    break;
                case 4:
                    newBuyer.buy();
                    break;
                case 5:
                    System.out.println("\nЧтобы посмотреть трекер заказов, введите номер." +
                                     "\nЧтобы вернуться в начальное меню нажмите Enter.");
                    int orderNumber = new Scanner(System.in).nextInt();
                    if (Buyer.buyers.containsKey(orderNumber)){
                        Buyer.buyers.get(orderNumber).seeTracker(orderNumber);
                    } else {
                        System.out.print("\nЗаказа с номером " + orderNumber + " не найдно.");
                    }
                    break;
            }
        }
        System.out.println("\nДо свидания!");
    }
}
