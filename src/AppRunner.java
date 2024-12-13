import enums.ActionLetter;
import model.*;
import util.UniversalArray;
import util.UniversalArrayImpl;

import java.util.Scanner;

public class AppRunner {

    private final UniversalArray<Product> products = new UniversalArrayImpl<>();

//    private final CoinAcceptor coinAcceptor;
//    private final CashAcceptor cashAcceptor;
    private PaymentAcceptor paymentAcceptor;


    private static boolean isExit = false;

    private AppRunner() {
        products.addAll(new Product[]{
                new Water(ActionLetter.B, 20),
                new CocaCola(ActionLetter.C, 50),
                new Soda(ActionLetter.D, 30),
                new Snickers(ActionLetter.E, 80),
                new Mars(ActionLetter.F, 80),
                new Pistachios(ActionLetter.G, 130)
        });
        paymentAcceptor = new CoinAcceptor(0);
    }

    public static void run() {
        System.out.println("Выберите способ оплаты:");
        System.out.println("1 - Монеты");
        System.out.println("2 - Купюры");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        PaymentAcceptor paymentAcceptor;
        if (choice == 1) {
            paymentAcceptor = new CoinAcceptor(0);
        } else if (choice == 2) {
            paymentAcceptor = new CashAcceptor(0);
        } else {
            System.out.println("Неверный выбор. Запуск с монетоприемником по умолчанию.");
            paymentAcceptor = new CoinAcceptor(0);
        }
        AppRunner app = new AppRunner();
        while (!isExit) {
            app.startSimulation();
        }
    }

    private void startSimulation() {
        print("В автомате доступны:");
        showProducts(products);

        print("Средств на сумму: " + paymentAcceptor.getAmount());

        UniversalArray<Product> allowProducts = new UniversalArrayImpl<>();
        allowProducts.addAll(getAllowedProducts().toArray());
        chooseAction(allowProducts);

    }

    private UniversalArray<Product> getAllowedProducts() {
        UniversalArray<Product> allowProducts = new UniversalArrayImpl<>();
        for (int i = 0; i < products.size(); i++) {
            if (paymentAcceptor.getAmount() >= products.get(i).getPrice()) {
                allowProducts.add(products.get(i));
            }
        }
        return allowProducts;
    }

    private void chooseAction(UniversalArray<Product> products) {
        print(" a - Пополнить баланс");
        showActions(products);
        print(" h - Выйти");
        String action = fromConsole().substring(0, 1);
        if ("a".equalsIgnoreCase(action)) {
            paymentAcceptor.addMoney( 10);
            print("Вы пополнили баланс на 10");
            return;
        }
        try {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getActionLetter().equals(ActionLetter.valueOf(action.toUpperCase()))) {
                    paymentAcceptor.substractMoney(products.get(i).getPrice());
                    print("Вы купили " + products.get(i).getName());
                    break;
                }
            }
        } catch (IllegalArgumentException e) {
            if ("h".equalsIgnoreCase(action)) {
                isExit = true;
            } else {
                print("Недопустимая буква. Попрбуйте еще раз.");
                chooseAction(products);
            }
        }


    }

    private void showActions(UniversalArray<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            print(String.format(" %s - %s", products.get(i).getActionLetter().getValue(), products.get(i).getName()));
        }
    }

    private String fromConsole() {
        return new Scanner(System.in).nextLine();
    }

    private void showProducts(UniversalArray<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            print(products.get(i).toString());
        }
    }

    private void print(String msg) {
        System.out.println(msg);
    }


}

