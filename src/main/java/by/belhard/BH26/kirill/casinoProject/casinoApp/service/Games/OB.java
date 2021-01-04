package by.belhard.BH26.kirill.casinoProject.casinoApp.service.Games;

import by.belhard.BH26.kirill.casinoProject.casinoApp.io.IOInterface;
import by.belhard.BH26.kirill.casinoProject.casinoApp.model.Account;
import by.belhard.BH26.kirill.casinoProject.casinoApp.service.AccountService;
import by.belhard.BH26.kirill.casinoProject.casinoApp.service.impl.AccountServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

public class OB {
    public static void start(Account account, IOInterface ioInterface) throws SQLException {


        String MENU_LEGEND =
                "\tPrice 1 \n" +
                        "\t1.  Play\n" +
                        "\te. exit\n";


        String input = "0";
        while (input != "e") {
            System.out.println(MENU_LEGEND);
            try {
                input = ioInterface.readStringValue();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

            switch (input) {
                case "1":
                    System.out.println("\tPlay\n");
                    OB.play(account);
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
        int proverka1=accountService.getBalance(account);
        accountService.getMoney(account, 1);
        int proverka2=accountService.getBalance(account);
        if(proverka1==proverka2)return;
        Random random = new Random();
        int a, b, c, win;
        a = random.nextInt(7) + 1;
        b = random.nextInt(7) + 1;
        c = random.nextInt(7) + 1;
        System.out.printf("\t|%d|%d|%d|\n", a, b, c);
        if (a == b && b == c) {
            win = 5 * a;
            accountService.putMoney(account, win);
            accountService.gameToursUpdate(account,"OB",(win-1));
            System.out.println("\tYou Won " + (win) + '\n');
        } else if (a == b && b != c || a == c && b != c) {
            win = a;
            accountService.putMoney(account, win);
            accountService.gameToursUpdate(account,"OB",(win-1));
            System.out.println("\tYou Won " + win + '\n');
        } else if (b == c && a != c) {
            win = b;
            accountService.putMoney(account, win);
            accountService.gameToursUpdate(account,"OB",(win-1));
            System.out.println("\tYou Won " + win + '\n');
        }accountService.gameToursUpdate(account,"OB",-1);
        System.out.println("\tBalance"+accountService.getBalance(account)+'\n');


    }
}