package by.belhard.BH26.kirill.casinoProject.casinoApp;

import by.belhard.BH26.kirill.casinoProject.casinoApp.io.ConsoleIO;
import by.belhard.BH26.kirill.casinoProject.casinoApp.io.IOInterface;
import by.belhard.BH26.kirill.casinoProject.casinoApp.model.Account;
import by.belhard.BH26.kirill.casinoProject.casinoApp.service.AccountService;
import by.belhard.BH26.kirill.casinoProject.casinoApp.service.impl.AccountServiceImpl;
import by.belhard.BH26.kirill.casinoProject.casinoApp.service.GamesStart;

import java.io.IOException;
import java.sql.SQLException;

public class CasinoAppController {

    private final IOInterface ioInterface;
    private final AccountService accountService;

    public CasinoAppController() {

        this.ioInterface = new ConsoleIO();
        this.accountService = new AccountServiceImpl();

    }

    public void start() {

        // infinite loop
        while (true) {
            /// login loop
            System.out.println("Enter username and password or e to exit");
            Account account = null;

            while (account == null) {
                try {
                    String username = ioInterface.readStringValue();
                    if (username.equals("e")) System.exit(0);
                    String password = ioInterface.readStringValue();
                    account = accountService.login(username, password);

                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }

            /// main work loop
            boolean cont = true;
            while (cont) {
                cont = mainProcess(account);
            }
        }
    }

    private boolean mainProcess(Account account) {

        System.out.println(IOInterface.MENU_LEGEND);

        String input = "";
        try {
            input = ioInterface.readStringValue();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        try {
            switch (input) {
                case "1":
                    System.out.println("balance");
                    int balance = accountService.getBalance(account);
                    System.out.println(balance);
                    break;
                case "2":
                    try {
                        System.out.println("how much to put");
                        int amount = ioInterface.readIntValue();
                        accountService.putMoney(account, amount);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case "3":
                    try {
                        System.out.println("how much to get");
                        int amount = ioInterface.readIntValue();
                        accountService.getMoney(account, amount);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case "4":
                    try {
                        System.out.println("choose game");
                        GamesStart.start(account,ioInterface);

                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case "5":
                    try {
                        System.out.println("Statistic");
                        accountService.viewMyStatistic(account);

                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case "e":
                    return false;
                default:
                    System.err.println("Wrong input");
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}

