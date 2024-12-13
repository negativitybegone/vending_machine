package model;

import java.util.Scanner;

public class CoinAcceptor implements PaymentAcceptor {
    private int amount;

    public CoinAcceptor(int amount) {
        this.amount = amount;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void addMoney(int amount) {
        this.amount += amount;
    }

    @Override
    public void substractMoney(int amount) {
        this.amount -= amount;
    }

    @Override
    public void handlePaymentInput() {
        System.out.println("Выберите номинал монеты:");
        System.out.println("1 - 1");
        System.out.println("2 - 3");
        System.out.println("3 - 5");
        System.out.println("4 - 10");
        System.out.println("5 - Вернуться назад");

        String choice = new Scanner(System.in).nextLine().trim().toLowerCase();

        switch (choice) {
            case "1":
                addMoney(1);
                System.out.println("Вы добавили 1.");
                break;
            case "2":
                addMoney(3);
                System.out.println("Вы добавили 3.");
                break;
            case "3":
                addMoney(5);
                System.out.println("Вы добавили 5.");
                break;
            case "4":
                addMoney(10);
                System.out.println("Вы добавили 10.");
                break;
            case "5":
                System.out.println("Возврат в главное меню.");
                return;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.");
                handlePaymentInput();
        }
    }
}

