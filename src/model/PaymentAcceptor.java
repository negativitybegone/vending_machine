package model;

public  interface PaymentAcceptor {
    int getAmount();

    void addMoney(int amount);

    void substractMoney(int amount);

    void handlePaymentInput();
}

