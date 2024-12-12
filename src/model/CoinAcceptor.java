package model;

public class CoinAcceptor extends PaymentAcceptor{

    public CoinAcceptor(int amount) {
        super(amount);
    }

    @Override
    public void handlePaymentInput() {

    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
