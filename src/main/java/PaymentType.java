public enum PaymentType {   //Способ оплаты
    PAYMENT_BY_CARD("Оплата банковской картой");
    String typeName;

    PaymentType(String typeName) {
        this.typeName = typeName;
    }
}