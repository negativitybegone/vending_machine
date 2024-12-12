package model;

public abstract class PaymentAcceptor {
    protected int amount;

    public PaymentAcceptor(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public abstract void handlePaymentInput();
}
