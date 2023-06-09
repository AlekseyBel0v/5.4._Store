# Интернет-магазин продуктов питания
___
## Краткое описание программы
Программа представляет собой интернет-магазин продуктов. В коде реализована интерфейсная часть покупателя. Возможно при 
код описывает логику в объеме больше той, которая требовалась по заданию. Но хотелось потренироваться использовать
некоторые шаблоны из модуля и немного попрактиковаться в умении структурировать классы и интерфейсы. Вероятно допущено 
много ошибок, так как программу по своему функционалу больше этой еще не приходилось писать. Был бы рад советам по написанию
кода.

## Правило Magic
В своем коде я затрудняюсь найти пример выполнения данного правила, так как в программе, как правило не закладывались
какие-либо константы. В основном используются только переменные, значение которых зависит только от введенных значений.

## Правило DRY
Примеры соблюдения правила DRY можно встретить в разных местах. Так, например, в классе ProductBase в 3 методах надо проверить, 
чтобы при вводе id продукта в продуктовой базе данных действительно существовал такой id. Для этого, чтобы
не повторять одну логику несколько раз, прописан метод checkID:
```java
void checkID(int ID) throws IllegalArgumentException {
    if (products.get(ID) == null) {
        throw new IllegalArgumentException("Товар с ID " + ID + " отсутствует");
    }
}
```
## SOLID
##S
В коде данной программы принцип Single-Responsibility используется во всех классах. Так, например, чтобы посмотерть трекер
заказов покупатель (`class Buyer`) не обращается на прямую к переменным класса `Tracker`. Он может только обратиться к самому 
Трекеру через метод `void seeTracker(int userID)` и попросить его сообщить о "своих" заказах.
Класс Трекер в своию очередь только следит и управляет заказами, но сам внутри их не структурирует. За структуру заказа 
отдельно отвечает класс Order и т.д.

##О
Хорошим примером принципа открытости-закрытости является структура ролей в магазине. Все пользователи описываются классом
`User`. Всех пользователей объединяет то, что каждый имеет ID, имя, почту, пароль и т.д. Класс `Buyer` наследуется от `User`.
Также потом можно создать и класс Manager, который будет, например вбивать новый товар в проуктовую базу. Но у него 
сохраниться состояние и поведение от `User`.
Еще одним примером могут быть сортировщики, которые реализуют интерфейс `Sortable`. По принципу шаблона Декоратор все
сортировщики реализуют метод `getOrderedProductList()`. В результате для того, чтобы добавить новый сортировщик, необходимо
создать новый класс, реализовава только один метод из данного интерфейса и конструктор. Но логика остальных классов остается
без изменений.

##L
Хорошим примером принципа Лискова-Барбары может быть таже реализация ролей, которая описана выше (в принципе открытости-закрытости).

##I
Принцип сегрегации интерфейса использован в классе `BankCard`. Данный класс реализует только 2 метода интерфейса `Payment`.
Далее могут быть добавлены или отключены классы, которые также реализуют этот интерфейс. Несмотря на это код можно запустить,
без остальных реализаций. Было бы неправильно в классе `Payment` прописать сразу все возможные способы оплаты.

##D
Принцип инверсии зависимостей можно рассмотреть на примере класса `Buyer`. У Покупателя могут быть свои особенности. Например,
ему могут быть доступны специальные предложения, в зависимости от которых будет формироваться цена товара. В дальнейшем 
эту задачу можно будет решить унаследовав Покупателя со скидкой от обычного Покупателя, расширив состояние нового класса
наличием доп. скидки, и реализовав дополнительно интерфейс "купить со скидкой".