package com.sandromax.honestbank.model.dao.impl;

import com.sandromax.honestbank.domain.account.Account;
import com.sandromax.honestbank.domain.account.AccountType;
import com.sandromax.honestbank.domain.user.User;
import com.sandromax.honestbank.model.dao.GenericDao;
import com.sandromax.honestbank.model.dao.connection.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;

public class AccountDao implements GenericDao<Account> {

    private static final Logger logger = Logger.getLogger(AccountDao.class.getName());

    private static final String SQL_CREATE_ACCOUNT = "INSERT INTO account (type_id, user_id, balance, rate, validity_from, validity_to) VALUES(?, ?, ?, ?, ?, ?);";
    private static final String SQL_FIND_ALL_ACCOUNTS = "SELECT * FROM account;";
    private static final String SQL_FIND_ACCOUNT_BY_ID = "SELECT * FROM account WHERE id = ?;";
//    private static final String SQL_FIND_ACCOUNT_BY_NAME = "SELECT * FROM account WHERE name = ?;";
    private static final String SQL_UPDATE_ACCOUNT = "UPDATE account SET type_id = ?, user_id = ?, balance = ?, rate = ?, validity_to = ? WHERE id = ?;";
    private static final String SQL_DELETE_ACCOUNT = "DELETE FROM account WHERE id = ?;";
    private static final String SQL_FIND_ACCOUNTS_BY_USER_ID = "SELECT * FROM account WHERE user_id = ?;";
    private static final String SQL_FIND_ACCOUNTS_BY_USER_ID_AND_TYPE_ID = "SELECT * FROM account WHERE user_id = ? AND type_id = ?;";

    @Override
    public int create(Account entity) {
        int newGeneratedId = 0;

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_CREATE_ACCOUNT, Statement.RETURN_GENERATED_KEYS)) {

            AccountTypeDao accountTypeDao = new AccountTypeDao();
            int typeId = accountTypeDao.findId(entity.getType());
            statement.setInt(1, typeId);

            int userId = entity.getUser().getIdInDb();
            statement.setInt(2, userId);

            statement.setDouble(3, entity.getBalance());

            statement.setDouble(4, entity.getRate());

            Date validityFrom = Date.valueOf(entity.getValidityFrom());
            statement.setDate(5, validityFrom);

            Date validityTo = Date.valueOf(entity.getValidityTo());
            statement.setDate(6, validityTo);


            statement.executeUpdate();

            //  return new new generated id
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next())
                newGeneratedId = rs.getInt(1);

        } catch (SQLException e) {
            logger.trace(e.getMessage());
        }

        return newGeneratedId;
    }

    @Override
    public LinkedList<Account> findAll() {
        LinkedList<Account> accounts = new LinkedList<>();
        ResultSet resultSet = null;

        try(Connection connection = ConnectionPool.getConnection();
            Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(SQL_FIND_ALL_ACCOUNTS);

            while (resultSet.next()) {
                int idInDb = resultSet.getInt("id");

                int typeId = resultSet.getInt("type_id");
                AccountTypeDao accountTypeDao = new AccountTypeDao();
                AccountType type = accountTypeDao.findById(typeId);

                int userId = resultSet.getInt("user_id");
                UserDao userDao = new UserDao();
                User user = userDao.findById(userId);

                double balance = resultSet.getDouble("balance");

                double rate = resultSet.getDouble("rate");

                LocalDate validityFrom = resultSet.getDate("validity_from").toLocalDate();

                LocalDate validityTo = resultSet.getDate("validity_to").toLocalDate();


                Account account = new Account(idInDb, type,
                user, balance, validityFrom, validityTo, rate);

                accounts.add(account);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return accounts;
    }

    @Override
    public Account findById(int id) {
        Account account = null;
        ResultSet resultSet = null;

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ACCOUNT_BY_ID)) {

            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
//                int idInDb = resultSet.getInt(1);

                int typeId = resultSet.getInt("type_id");
                AccountTypeDao accountTypeDao = new AccountTypeDao();
                AccountType type = accountTypeDao.findById(typeId);

                int userId = resultSet.getInt("user_id");
                UserDao userDao = new UserDao();
                User user = userDao.findById(userId);

                double balance = resultSet.getDouble("balance");

                double rate = resultSet.getDouble("rate");

                LocalDate validityFrom = resultSet.getDate("validity_from").toLocalDate();

                LocalDate validityTo = resultSet.getDate("validity_to").toLocalDate();


                account = new Account(id, type, user, balance, validityFrom, validityTo, rate);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return account;
    }

    @Override
    public boolean update(Account entity) {
        boolean result = false;

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ACCOUNT)) {

            AccountTypeDao accountTypeDao = new AccountTypeDao();
            int typeId = accountTypeDao.findId(entity.getType());
            statement.setInt(1, typeId);

            int userId = entity.getUser().getIdInDb();
            statement.setInt(2, userId);

            statement.setDouble(3, entity.getBalance());

            statement.setDouble(4, entity.getRate());

            Date validityTo = Date.valueOf(entity.getValidityTo());
            statement.setDate(5, validityTo);

            statement.setInt(6, entity.getIdInDb());

            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean delete(Account entity) {
        boolean result = false;

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ACCOUNT)) {

            statement.setInt(1, entity.getIdInDb());

            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public LinkedList<Account> findByUserEmail(String email) {
        LinkedList<Account> accounts = new LinkedList<>();
        ResultSet resultSet = null;

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ACCOUNTS_BY_USER_ID)) {

            UserDao userDao = new UserDao();
            User user = userDao.findByEmail(email);

            statement.setInt(1, user.getIdInDb());
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idInDb = resultSet.getInt("id");

                int typeId = resultSet.getInt("type_id");
                AccountTypeDao accountTypeDao = new AccountTypeDao();
                AccountType type = accountTypeDao.findById(typeId);

                double balance = resultSet.getDouble("balance");

                double rate = resultSet.getDouble("rate");

                LocalDate validityFrom = resultSet.getDate("validity_from").toLocalDate();

                LocalDate validityTo = resultSet.getDate("validity_to").toLocalDate();


                Account account = new Account(idInDb, type, user, balance, validityFrom, validityTo, rate);
                accounts.add(account);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return accounts;
    }

    public LinkedList<Account> findByUserAndAccountType(User user, AccountType type) {
        LinkedList<Account> accounts = new LinkedList<>();
        ResultSet resultSet = null;

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ACCOUNTS_BY_USER_ID_AND_TYPE_ID)) {

            int userId = user.getIdInDb();
            if(userId == 0) {
                UserDao userDao = new UserDao();
                user = userDao.findByEmail(user.getEmail());
                userId = user.getIdInDb();
            }
            statement.setInt(1, user.getIdInDb());

            AccountTypeDao accountTypeDao = new AccountTypeDao();
            int typeId = accountTypeDao.findId(type);
            statement.setInt(2, typeId);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idInDb = resultSet.getInt("id");

//                int typeId = resultSet.getInt(2);
//                AccountTypeDao accountTypeDao = new AccountTypeDao(logger);
//                AccountType type = accountTypeDao.findById(typeId);

                double balance = resultSet.getDouble("balance");

                double rate = resultSet.getDouble("rate");

                LocalDate validityFrom = resultSet.getDate("validity_from").toLocalDate();

                LocalDate validityTo = resultSet.getDate("validity_to").toLocalDate();


                Account account = new Account(idInDb, type, user, balance, validityFrom, validityTo, rate);
                accounts.add(account);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return accounts;
    }
}
