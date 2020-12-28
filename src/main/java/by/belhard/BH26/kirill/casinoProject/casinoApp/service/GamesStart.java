package by.belhard.BH26.kirill.casinoProject.casinoApp.service;

import by.belhard.BH26.kirill.casinoProject.casinoApp.io.IOInterface;
import by.belhard.BH26.kirill.casinoProject.casinoApp.model.Account;
import by.belhard.BH26.kirill.casinoProject.casinoApp.service.Games.BlackJack;
import by.belhard.BH26.kirill.casinoProject.casinoApp.service.Games.Bones;
import by.belhard.BH26.kirill.casinoProject.casinoApp.service.Games.OB;
import by.belhard.BH26.kirill.casinoProject.casinoApp.service.Games.Ruletka;

import java.io.IOException;
import java.sql.SQLException;

public interface GamesStart {
     String MENU_LEGEND =
            "\t1. 'Odnorukiy Bandit'\n" +
                    "\t2. Ruletka\n" +
                    "\t3. BlackJack\n" +
                    "\t4. Bones\n" +
                    "\te. exit\n";


     static void start(Account account, IOInterface ioInterface) throws SQLException, IOException {

        String input = "";
        while (input != "e") {
            System.out.println(MENU_LEGEND);
        try {
            input = ioInterface.readStringValue();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

            switch (input) {
                case "1":
                    System.out.println("OB");
                    OB.start(account,ioInterface);
                    break;
                case "2":
                    System.out.println("Ruletka");
                    Ruletka.start(account,ioInterface);
                    break;
                case "3":
                    System.out.println("BlackJack");
                    BlackJack.start(account,ioInterface);
                    break;
                case "4":
                    System.out.println("Bones");
                    Bones.start(account,ioInterface);
                    break;
                case "e":
                    input="e";
                    break;
                default:
                    System.err.println("Wrong input");
                    break;
            }
        }


    }
}
