package by.belhard.BH26.kirill.casinoProject.casinoApp.service.Games;

import by.belhard.BH26.kirill.casinoProject.casinoApp.io.IOInterface;
import by.belhard.BH26.kirill.casinoProject.casinoApp.model.Account;

import java.io.IOException;
import java.sql.SQLException;

public class BlackJack {
    public static void start(Account account, IOInterface ioInterface) throws SQLException {


        String MENU_LEGEND =
                        "\t1. Press Enter to play\n" +
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
                case "":
                    System.out.println("\tPlay\n");
                    BlackJack.play(account);
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

    private static void play(Account account) {

        System.out.println("ПОКА НЕТ РЕАЛИЗАЦИИ");

    }


}
