package by.belhard.BH26.kirill.casinoProject.casinoApp.service.impl;

import by.belhard.BH26.kirill.casinoProject.casinoApp.model.Account;
import by.belhard.BH26.kirill.casinoProject.casinoApp.repository.AccountRepository;
import by.belhard.BH26.kirill.casinoProject.casinoApp.repository.impl.AccountRepositoryImpl;
import by.belhard.BH26.kirill.casinoProject.casinoApp.service.AccountService;

import java.sql.SQLException;

public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl() {

        accountRepository = new AccountRepositoryImpl();
    }

    @Override
    public Account login(String user, String pass) {

        Account account = null;

        try {
            account = accountRepository.getByName(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (account != null && account.getPassword().equals(pass)) {
            return account;
        }

        return account;
    }

    @Override
    public int getBalance(Account account) throws SQLException {


        return accountRepository.getBalance(account.getUsername());
    }

    @Override
    public void putMoney(Account account, int amount) throws SQLException {
        // check if amount > 0
        if (amount <=0) {
            System.err.println("Зачислить на счет можно только положительное число");
            return;
        }
        int newValue = account.getMoney() + amount;
        account.setMoney(newValue);

        accountRepository.updateMoney(account.getUsername(), newValue);
    }

    @Override
    public void getMoney(Account account, int amount) throws SQLException {
        // check if account has enough money

        int newValue = account.getMoney() - amount;
        if (newValue >= 0) {
            account.setMoney(newValue);
            accountRepository.updateMoney(account.getUsername(), newValue);
        }else {
        System.err.println("Недостаточно средств на счету");
        return;}

    }

    @Override
    public void gameToursUpdate(Account account, String gamename, String winlose) throws SQLException {

        accountRepository.gameToursUpdate(account, gamename, winlose);
    }

    @Override
    public void viewMyStatistic(Account account) throws SQLException {
        accountRepository.getStatistic(account);
    }
}