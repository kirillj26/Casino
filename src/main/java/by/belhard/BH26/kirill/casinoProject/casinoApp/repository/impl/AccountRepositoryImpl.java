package by.belhard.BH26.kirill.casinoProject.casinoApp.repository.impl;

import by.belhard.BH26.kirill.casinoProject.casinoApp.model.Account;
import by.belhard.BH26.kirill.casinoProject.casinoApp.repository.AccountRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRepositoryImpl implements AccountRepository {

    private static final String GET_BY_NAME_QUERY =
            "select * from accounts where username = ?";
    private static final String GET_BALANCE_BY_NAME_QUERY =
            "select money from accounts where username = ?";
    private static final String UPDATE_BALANCE_BY_NAME_QUERY =
            "update accounts set money = ? where username = ?";
    private static final String INSERT_NEW_INTO_GAMETOURS =
            "insert into gametours value(null,?,?,default,?)";
    private static final String GET_MY_STATISTIC_GAMETOURS =
            "select * from gametours where username=?";

    @Override
    public Account getByName(String name) throws SQLException {

        Connection connection = ConnectionImpl.getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_BY_NAME_QUERY);
        statement.setString(1, name);

        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next())
            throw new SQLException("No such account");

        int id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        int money = resultSet.getInt("money");

        return Account.builder().id(id).username(username).password(password).money(money).build();
    }

    @Override
    public int getBalance(String name) throws SQLException {

        Connection connection = ConnectionImpl.getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_BALANCE_BY_NAME_QUERY);
        statement.setString(1, name);

        ResultSet resultSet = statement.executeQuery();

        resultSet.next();

        return resultSet.getInt("money");
    }

    @Override
    public void updateMoney(String name, int amount) throws SQLException {

        Connection connection = ConnectionImpl.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_BALANCE_BY_NAME_QUERY);
        statement.setInt(1, amount);
        statement.setString(2, name);

        statement.executeUpdate();
    }

    @Override
    public void gameToursUpdate(Account account, String gamename, String winlose) throws SQLException {
        Connection connection = ConnectionImpl.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT_NEW_INTO_GAMETOURS);
        statement.setString(1, gamename);
        statement.setString(2, account.getUsername());
        statement.setString(3, winlose);
        statement.executeUpdate();

    }

    @Override
    public void getStatistic(Account account) throws SQLException {
        Connection connection = ConnectionImpl.getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_MY_STATISTIC_GAMETOURS);
        statement.setString(1, account.getUsername());
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next())
            throw new SQLException("No such account");
        System.out.printf("\t\t%-4s%-10s%-12s%-26s%-4s\n", "id", "gamename", "username", "datetime", "winlose");


        while (resultSet.next()) {
            System.out.printf("\t\t%-4s%-10s%-12s%-26s%-4s\n", resultSet.getInt("id"),
                    resultSet.getString("gamename"),
                    resultSet.getString("username"),
                    resultSet.getTimestamp("time"),
                    resultSet.getString("winlose"));

        }


    }

}