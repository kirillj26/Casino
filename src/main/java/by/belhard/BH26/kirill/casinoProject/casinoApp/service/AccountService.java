package by.belhard.BH26.kirill.casinoProject.casinoApp.service;

import by.belhard.BH26.kirill.casinoProject.casinoApp.model.Account;

import java.sql.SQLException;

public interface AccountService {

    Account login(String user, String pass);

    int getBalance(Account account) throws SQLException;

    void putMoney(Account account, int amount) throws SQLException;

    void getMoney(Account account, int amount) throws SQLException;

    void gameToursUpdate(Account account,String gamename,String winlose) throws  SQLException;

    void viewMyStatistic(Account account) throws SQLException;
}
