package by.belhard.BH26.kirill.casinoProject.casinoApp.service.Games;

import by.belhard.BH26.kirill.casinoProject.casinoApp.io.IOInterface;
import by.belhard.BH26.kirill.casinoProject.casinoApp.model.Account;
import by.belhard.BH26.kirill.casinoProject.casinoApp.repository.AccountRepository;
import by.belhard.BH26.kirill.casinoProject.casinoApp.service.AccountService;
import by.belhard.BH26.kirill.casinoProject.casinoApp.service.impl.AccountServiceImpl;


import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class Ruletka {

    private static Map<String, Integer> betsResult = new HashMap<>();
    private static List bets = new ArrayList<>();
    private static int betSize = 999;


    public static void start(Account account, IOInterface ioInterface) throws SQLException, IOException {


        String MENU_LEGEND =
                "\t1. Play\n" +
                        "\t2. Размер ставки\n" +
                        "\t3. Сделать ставку\n" +
                        "\te. exit\n";


        String input = "0";
        while (!input.equals("e")) {
            System.out.println(MENU_LEGEND);
            try {
                input = ioInterface.readStringValue();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

            switch (input) {
                case "1":
                    System.out.println("\tPlay\n");
                    Ruletka.play(account);
                    break;
                case "2":
                    System.out.println("Введите размер ставки от 10 до 100");
                    int b = ioInterface.readIntValue();
                    if (b < 10 || b > 100) {
                        System.err.println("Неправильный размер ставки");
                        break;
                    }
                    betSize = b;
                    break;
                case "3":
                    Ruletka.bet(ioInterface, account);
                    break;
                case "e":
                    if (bets.isEmpty()) {
                        input = "e";
                        break;
                    } else {
                        System.out.println("Вы сделали ставки нажмите играть");
                        input = "0";
                        break;
                    }
                default:
                    System.err.println("Wrong input");
                    break;
            }


        }

    }

    private static void bet(IOInterface ioInterface, Account account) throws IOException, SQLException {
        if (betSize < 10 || betSize > 100) {
            System.err.println("Вы не задали размер ставки");
            return;
        }
        final AccountService accountService;
        accountService = new AccountServiceImpl();
        String MENU_LEGEND =
                "\t1. Ставка на 1 число\n" +
                        "\t2. Ставка на цвет\n" +
                        "\t3. Ставка четное/нечетное\n" +
                        "\t4. Ставка 1-12,13-24,25-36\n" +
                        "\t5. Ставка 1-18,19-36\n" +
                        "\te. exit\n";


        String input = "0";
        while (!input.equals("e")) {
            if (accountService.getBalance(account) == 0) {
                System.err.println("Нет денег для дальнейших ставок");
                return;
            }
            if (betSize > accountService.getBalance(account)) {
                System.err.println("Недостаточно денег для ставки");
                return;
            }
            System.out.println(MENU_LEGEND);
            try {
                input = ioInterface.readStringValue();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            String bet;
            switch (input) {
                case "1":
                    System.out.println("Введите число 0-36");
                    bet = ioInterface.readStringValue();
                    if (bets.contains(bet)) {
                        System.err.println("Вы уже делали ставку на это число");
                        break;
                    }
                    if (Integer.parseInt(bet) > 36 || Integer.parseInt(bet) < 0) {
                        System.err.println("Wrong input");
                        break;
                    }
                    bets.add(bet);
                    betsResult.put(bet, betSize);
                    accountService.getMoney(account, betSize);
                    System.out.println("\tставка на " + bet + "\n");
                    break;
                case "2":

                    if (bets.contains("Black") || bets.contains("Red")) {
                        System.err.println("Вы уже делали ставку на цвет");
                        break;
                    }
                    System.out.println("Введите цвет Red/Black");
                    bet = ioInterface.readStringValue();
                    if (bet.equals("Black") || bet.equals("Red")) {
                        bets.add(bet);
                        betsResult.put(bet, betSize);
                        accountService.getMoney(account, betSize);
                        System.out.println("\tставка на " + bet + "\n");
                        break;
                    }
                    System.err.println("Wrong input");
                    break;
                case "3":
                    if (bets.contains("Even") || bets.contains("Odd")) {
                        System.err.println("Вы уже делали ставку на четность");
                        break;
                    }
                    System.out.println("Введите  Even/Odd");
                    bet = ioInterface.readStringValue();
                    if (bet.equals("Even") || bet.equals("Odd")) {
                        bets.add(bet);
                        betsResult.put(bet, betSize);
                        accountService.getMoney(account, betSize);
                        System.out.println("\tставка на " + bet + "\n");
                        break;
                    }
                    System.err.println("Wrong input");
                    break;
                case "4":
                    if (bets.contains("1-12") || bets.contains("13-24") || bets.contains("25-36")) {
                        System.err.println("Вы уже делали ставку на дюжины");
                        break;
                    }
                    System.out.println("Введите 1-12/13-24/25-36 ");
                    bet = ioInterface.readStringValue();
                    if (bet.equals("1-12") || bet.equals("13-24") || bet.equals("25-36")) {
                        bets.add(bet);
                        betsResult.put(bet, betSize);
                        accountService.getMoney(account, betSize);
                        System.out.println("\tставка на " + bet + "\n");
                        break;
                    }
                    break;
                case "5":
                    if (bets.contains("1-18") || bets.contains("19-36")) {
                        System.err.println("Вы уже делали ставку на половинки");
                        break;
                    }
                    System.out.println("Введите 1-18/19-36 ");
                    bet = ioInterface.readStringValue();
                    if (bet.equals("1-18") || bet.equals("19-36")) {
                        bets.add(bet);
                        betsResult.put(bet, betSize);
                        accountService.getMoney(account, betSize);
                        System.out.println("\tставка на " + bet + "\n");
                        break;
                    }
                    break;
                case "e":
                    input = "e";
                    break;
                default:
                    System.err.println("Wrong input");
                    break;
            }


        }


    }

    private static void play(Account account) throws SQLException {
        final AccountService accountService;
        accountService = new AccountServiceImpl();
        int resultwin = 0;
        int totalwin = 0;
        int[] red = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
        int[] black = {2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35};

        if (bets.isEmpty()) {
            System.err.println("Вы не сделали ставок");
            return;
        }
        if (betSize < 10 || betSize > 100) {
            System.err.println("Вы не задали размер ставки");
            return;
        }
        Random random = new Random();
        int ruletka = random.nextInt(37);
        System.out.println("Значение рулетки=" + ruletka);
        System.out.println(betsResult);

        for (Map.Entry<String, Integer> entry : betsResult.entrySet()) {
            try {
                for (int i = 0; i < 37; i++) {

                    if (i == Integer.parseInt(entry.getKey())) {
                        resultwin -= entry.getValue();
                    }
                }

                if (ruletka == Integer.parseInt(entry.getKey())) {
                    resultwin += entry.getValue() * 36;
                    totalwin += entry.getValue() * 36;
                }
            } catch (NumberFormatException e) {
            }

        }
        for (Map.Entry<String, Integer> entry : betsResult.entrySet()) {
            if (entry.getKey().equals("Red")) {
                resultwin -= entry.getValue();
                for (int x : red) {
                    if (ruletka == x) resultwin += entry.getValue() * 2;
                    if (ruletka == x) totalwin += entry.getValue() * 2;
                }
            }
            if (entry.getKey().equals("Black")) {
                resultwin -= entry.getValue();
                for (int x : black) {
                    if (ruletka == x) resultwin += entry.getValue() * 2;
                    if (ruletka == x) totalwin += entry.getValue() * 2;
                }
            }

        }
        for (Map.Entry<String, Integer> entry : betsResult.entrySet()) {
            if (entry.getKey().equals("1-18")) {
                resultwin -= entry.getValue();
                for (int x = 1; x < 19; x++) {
                    if (ruletka == x) resultwin += entry.getValue() * 2;
                    if (ruletka == x) totalwin += entry.getValue() * 2;
                }
            }
            if (entry.getKey().equals("19-36")) {
                resultwin -= entry.getValue();
                for (int x = 19; x < 37; x++) {
                    if (ruletka == x) resultwin += entry.getValue() * 2;
                    if (ruletka == x) totalwin += entry.getValue() * 2;
                }
            }

        }
        for (Map.Entry<String, Integer> entry : betsResult.entrySet()) {
            if (entry.getKey().equals("Even")) {
                resultwin -= entry.getValue();
                for (int x = 2; x < 37; x += 2) {
                    if (ruletka == x) resultwin += entry.getValue() * 2;
                    if (ruletka == x) totalwin += entry.getValue() * 2;
                }
            }
            if (entry.getKey().equals("Odd")) {
                resultwin -= entry.getValue();
                for (int x = 1; x < 37; x += 2) {
                    if (ruletka == x) resultwin += entry.getValue() * 2;
                    if (ruletka == x) totalwin += entry.getValue() * 2;
                }
            }
        }
        for (Map.Entry<String, Integer> entry : betsResult.entrySet()) {
            if (entry.getKey().equals("1-12")) {
                resultwin -= entry.getValue();
                for (int x = 1; x < 13; x++) {
                    if (ruletka == x) resultwin += entry.getValue() * 3;
                    if (ruletka == x) totalwin += entry.getValue() * 3;
                }
            }
            if (entry.getKey().equals("13-24")) {
                resultwin -= entry.getValue();
                for (int x = 13; x < 25; x++) {
                    if (ruletka == x) resultwin += entry.getValue() * 3;
                    if (ruletka == x) totalwin += entry.getValue() * 3;
                }
            }
            if (entry.getKey().equals("25-36")) {
                resultwin -= entry.getValue();
                for (int x = 25; x < 37; x++) {
                    if (ruletka == x) resultwin += entry.getValue() * 3;
                    if (ruletka == x) totalwin += entry.getValue() * 3;
                }
            }
        }


        System.out.println("Выигрыш " + resultwin);
        if (totalwin != 0) accountService.putMoney(account, totalwin);
        accountService.gameToursUpdate(account, "Ruletka",resultwin);
        bets = new

                ArrayList();

        betsResult = new HashMap<>();
        betSize = 999;

    }


}
