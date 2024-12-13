package model;

import java.util.Scanner;

public class CashAcceptor implements PaymentAcceptor{
    private int amount;

    public CashAcceptor(int amount) {
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
        System.out.println("Выберите номинал купюры:");
        System.out.println("1 - 10");
        System.out.println("2 - 20");
        System.out.println("3 - 50");
        System.out.println("4 - 100");
        System.out.println("5 - Вернуться назад");

        String choice = new Scanner(System.in).nextLine().trim().toLowerCase();

        switch (choice) {
            case "1":
                addMoney(10);
                System.out.println("Вы добавили 10.");
                break;
            case "2":
                addMoney(20);
                System.out.println("Вы добавили 20.");
                break;
            case "3":
                addMoney(50);
                System.out.println("Вы добавили 50.");
                break;
            case "4":
                addMoney(100);
                System.out.println("Вы добавили 100.");
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
