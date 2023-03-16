import java.util.List;

public interface IProduct {
    /**
     * Метод добавляет новую характеристику товара ProductCharacteristic и устанавливает ее значение characteristicValue
     * @param productCharacteristic
     * @param characteristicValue
     * @return
     */
    IProduct addCharacteristic(ProductCharacteristic productCharacteristic, Object characteristicValue);

    List<ProductCharacteristic> getListCharacteristics();

    /**
     * Метод возвращает значение переданной характеристики товара ProductCharacteristic. Если такая характеристика у товара
     * отсутствует, то метод возвращает null;
     */
    <E> E getCharacteristicValue(ProductCharacteristic ProductCharacteristic);

    /**
     * Метод возвращает списиок категорий товара, к которым он принадлежит
     * @return
     */
    List<Category> getListCategories();

    /**
     * Мтод возвращает описание, которое содержит информацию об ID, названии товара, производителе, группе и других
     * характреистиках.
     * @return
     */
    String getProductDescription();

    /**
     * Метод возвращает среднюю стоимость в пересчете на 1000 г товара
     * @return
     */
    double getApproximatePrice() throws RuntimeException;
}
