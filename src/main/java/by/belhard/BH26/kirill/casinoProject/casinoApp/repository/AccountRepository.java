package by.belhard.BH26.kirill.casinoProject.casinoApp.repository;

import by.belhard.BH26.kirill.casinoProject.casinoApp.model.Account;

import java.sql.SQLException;

public interface AccountRepository {
    Account getByName(String username) throws SQLException;

    int getBalance(String username) throws SQLException;

    void updateMoney(String username, int amount) throws SQLException;

    void gameToursUpdate(Account account,String gamename,int winlose) throws SQLException;

    void getStatistic(Account account) throws SQLException;

    void getTtop3All() throws SQLException;

    void getTtop3Gamename(String gamename) throws SQLException;




}
