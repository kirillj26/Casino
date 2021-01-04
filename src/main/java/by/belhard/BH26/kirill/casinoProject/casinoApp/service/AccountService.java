package by.belhard.BH26.kirill.casinoProject.casinoApp.service;

import by.belhard.BH26.kirill.casinoProject.casinoApp.io.IOInterface;
import by.belhard.BH26.kirill.casinoProject.casinoApp.model.Account;

import java.sql.SQLException;

public interface AccountService {

    Account login(String user, String pass);

    int getBalance(Account account) throws SQLException;

    void putMoney(Account account, int amount) throws SQLException;

    void getMoney(Account account, int amount) throws SQLException;

    void gameToursUpdate(Account account,String gamename,int winlose) throws  SQLException;

    void viewStatistic(Account account, IOInterface ioInterface) throws SQLException;
}
