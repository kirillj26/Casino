package by.belhard.BH26.kirill.casinoProject.casinoApp.service.Games;

import by.belhard.BH26.kirill.casinoProject.casinoApp.io.IOInterface;
import by.belhard.BH26.kirill.casinoProject.casinoApp.model.Account;
import by.belhard.BH26.kirill.casinoProject.casinoApp.service.AccountService;
import by.belhard.BH26.kirill.casinoProject.casinoApp.service.impl.AccountServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class BlackJack {

    private static int betSize = 999;
    private static int betSizeSumPlayer = 0;
    private static int betSizeSumDealer = 0;

    public static void start(Account account, IOInterface ioInterface) throws SQLException, IOException {


        String MENU_LEGEND =
                "\t1.  Play\n" +
                        "\t2. Размер ставки\n" +
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
                    BlackJack.play(account, ioInterface);
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
                case "e":
                    input = "e";
                    break;
                default:
                    System.err.println("Wrong input");
                    break;
            }


        }

    }

    private static void play(Account account, IOInterface ioInterface) throws SQLException {
        final AccountService accountService;
        accountService = new AccountServiceImpl();
        if (betSize < 10 || betSize > 100) {
            System.err.println("Вы не задали размер ставки");
            return;
        }
        if (accountService.getBalance(account) < betSize * 7) {
            System.err.println("На счету мало денег,выберете размер ставок поменьше" +
                    "\nНа счету должно хватать как минимум на 7 ставок");
            return;
        }


        final String[] SUITS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        final int[] RANK = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
        final char[] COLOUR = {'♥', '♦', '♣', '♠'};
        int cardcount = 0;
        int dealercardcount = 0;
        int playercardcount = 0;
        int playersum = 0;
        int dealersum = 0;
        List<Card> deck = new ArrayList<>();
        List<Card> playerhand = new ArrayList<>();
        List<Card> dealerhand = new ArrayList<>();

        for (int c = 0; c < 4; c++) {
            for (int s = 0; s < 13; s++) {
                Card newCard = new Card(SUITS[s], RANK[s], COLOUR[c]);
                deck.add(newCard);
            }
        }
        Collections.shuffle(deck);
        Collections.shuffle(deck);
        Collections.shuffle(deck);
        Collections.shuffle(deck);

        for (int i = 0; i < 2; i++) {
            playerhand.add(deck.get(cardcount));
            cardcount++;
            playersum += playerhand.get(playercardcount).getRank();
            playercardcount++;
            betSizeSumPlayer += betSize;
            if (playersum > 21) {
                System.out.println("Перебор у игрока,Дилер выиграл");
                accountService.getMoney(account, betSizeSumPlayer);
                accountService.gameToursUpdate(account, "BlackJack", -betSizeSumPlayer);
                BlackJack.showCardsAndUpdateVAlues(cardcount, dealerhand.toString(), dealersum, playerhand.toString(), playersum);
                return;
            }
        }
        for (int i = 0; i < 2; i++) {
            dealerhand.add(deck.get(cardcount));
            cardcount++;
            dealersum += dealerhand.get(dealercardcount).getRank();
            dealercardcount++;
            betSizeSumDealer += betSize;
            if (dealersum > 21) {
                System.out.println("Перебор у Дилера,игрок выиграл");
                accountService.putMoney(account, betSizeSumDealer);
                accountService.gameToursUpdate(account, "BlackJack", +betSizeSumDealer);
                BlackJack.showCardsAndUpdateVAlues(cardcount, dealerhand.toString(), dealersum, playerhand.toString(), playersum);
                return;
            }
        }

        if (playersum == 21) {
            if (playersum == dealersum) {
                System.out.println("Ничья ");
                accountService.gameToursUpdate(account, "BlackJack", 0);
            } else {
                System.out.println("Игрок выиграл ");
                accountService.putMoney(account, betSizeSumDealer);
                accountService.gameToursUpdate(account, "BlackJack", +betSizeSumDealer);
            }
            BlackJack.showCardsAndUpdateVAlues(cardcount, dealerhand.toString(), dealersum, playerhand.toString(), playersum);
            return;
        } else if (dealersum == 21) {
            System.out.println("Дилер выиграл ");
            accountService.getMoney(account, betSizeSumPlayer);
            accountService.gameToursUpdate(account, "BlackJack", -betSizeSumPlayer);
            BlackJack.showCardsAndUpdateVAlues(cardcount, dealerhand.toString(), dealersum, playerhand.toString(), playersum);
            return;
        }
        System.out.println("Карты игрока " + playerhand);
        System.out.println("Сумма игрока " + playersum);
        String input = "0";
        while (!(input.equals("e"))) {
            System.out.println("Взять еще карту Y/N?\n");
            try {
                input = ioInterface.readStringValue().toUpperCase();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            switch (input) {
                case "Y":
                    playerhand.add(deck.get(cardcount));
                    cardcount++;
                    playersum += playerhand.get(playercardcount).getRank();
                    playercardcount++;
                    betSizeSumPlayer += betSize;
                    System.out.println("Карты игрока " + playerhand);
                    System.out.println("Сумма игрока " + playersum);
                    if (playersum > 21) {
                        System.out.println("Перебор у игрока,Дилер выиграл");
                        accountService.getMoney(account, betSizeSumPlayer);
                        accountService.gameToursUpdate(account, "BlackJack", -betSizeSumPlayer);
                        BlackJack.showCardsAndUpdateVAlues(cardcount, dealerhand.toString(), dealersum, playerhand.toString(), playersum);
                        return;
                    }
                    break;
                case "N":
                    input = "e";
                    break;
                default:
                    System.err.println("Wrong input");
                    break;
            }
        }

        while (dealersum < 16) {
            dealerhand.add(deck.get(cardcount));
            cardcount++;
            dealersum += dealerhand.get(dealercardcount).getRank();
            dealercardcount++;
            betSizeSumDealer += betSize;
            if (dealersum > 21) {
                System.out.println("Перебор у Дилера,игрок выиграл");
                accountService.putMoney(account, betSizeSumDealer);
                accountService.gameToursUpdate(account, "BlackJack", +betSizeSumDealer);
                BlackJack.showCardsAndUpdateVAlues(cardcount, dealerhand.toString(), dealersum, playerhand.toString(), playersum);
                return;
            }
        }
        if (playersum == dealersum) {
            System.out.println("Ничья ");
            accountService.gameToursUpdate(account, "BlackJack", 0);
        } else if (playersum > dealersum) {
            System.out.println("Игрок выиграл ");
            accountService.putMoney(account, betSizeSumDealer);
            accountService.gameToursUpdate(account, "BlackJack", +betSizeSumDealer);
        } else {
            System.out.println("Дилер выиграл ");
            accountService.getMoney(account, betSizeSumPlayer);
            accountService.gameToursUpdate(account, "BlackJack", -betSizeSumPlayer);
        }
        BlackJack.showCardsAndUpdateVAlues(cardcount, dealerhand.toString(), dealersum, playerhand.toString(), playersum);

    }

    private static void showCardsAndUpdateVAlues(int cardcount, String dealerhand, int dealersum, String playerhand, int playersum) {
        System.out.println("Взято карт всего " + cardcount);
        System.out.println("Карты дилера " + dealerhand);
        System.out.println("Cумма дилера " + dealersum);
        System.out.println("Карты игрока " + playerhand);
        System.out.println("Сумма игрока " + playersum);
        betSize = 999;
        betSizeSumPlayer = 0;
        betSizeSumDealer = 0;

    }

}
