import java.util.List;

/**
 * Перечисление ProductCharacteristics предусмотрено во избежание создания в разных товарах одних и тех же характеристик
 * с разными именами. При условии соблюдения данного правила, возможно реализовать фильтр товаров по наличию/значению
 * отдельных характеристик.
 */

public enum ProductCharacteristic {
    ID("Штрих-код", Integer.class),
    PRODUCT_NAME("Название продукта", String.class),
    MANUFACTURER("Производитель", Manufacturer.class),
    COUNT("Колличество", Double.class),
    UNIT("Единица измерения", Unit.class),
    PRICE("Цена", Double.class),
    ALL_CATEGORIES("Категория товара", List.class),
    WEIGHT("Масса НЕТТО (г)", Integer.class),
    IS_VEGETARIAN("Вегетарианский продукт", Boolean.class);

    final String characteristicName;
    final Class type;   //для справочной информации

    ProductCharacteristic(String characteristicName, Class type) {
        this.characteristicName = characteristicName;
        this.type = type;
    }
}

enum Unit{
    PCS("шт."),
    G("г"),
    L("л"),
    KG("кг");

    final String dimencion;

    Unit(String dimancion) {
        this.dimencion = dimancion;
    }

    @Override
    public String toString() {
        return dimencion;
    }
}