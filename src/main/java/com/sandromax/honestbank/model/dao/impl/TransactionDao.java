package com.sandromax.honestbank.model.dao.impl;

import com.sandromax.honestbank.domain.account.Account;
import com.sandromax.honestbank.domain.account.Transaction;
import com.sandromax.honestbank.domain.service.log.Logger;
import com.sandromax.honestbank.model.dao.GenericDao;
import com.sandromax.honestbank.model.dao.connection.ConnectionPool;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class TransactionDao implements GenericDao<Transaction> {

    //todo Ask: logger
    public TransactionDao(Logger logger) {
        this.logger = logger;
    }

    private Logger logger;

    private static final String SQL_CREATE_TRANSACTION = "INSERT INTO transaction (amount, sender_account_id, recipient_account_id) VALUES(?, ?, ?);";
    private static final String SQL_FIND_ALL_TRANSACTIONS = "SELECT * FROM transaction;";
    private static final String SQL_FIND_TRANSACTION_BY_ID = "SELECT * FROM transaction WHERE id = ?;";

    //    private static final String SQL_DELETE_ACCOUNT = "DELETE FROM transaction WHERE id = ?;";
    private static final String SQL_FIND_TRANSACTIONS_BY_ACCOUNT_ID = "SELECT * FROM transaction sender_account_id = ? OR recipient_account_id;";

    @Override
    public int create(Transaction entity) {
        int newGeneratedId = 0;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CREATE_TRANSACTION, Statement.RETURN_GENERATED_KEYS)) {

            statement.setDouble(1, entity.getAmount());

            int senderAccountID = entity.getSender().getIdInDb();
            statement.setInt(2, senderAccountID);

            int recipientAccountId = entity.getRecipient().getIdInDb();
            statement.setInt(3, recipientAccountId);

            statement.executeUpdate();;

            //  return new new generated id
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next())
                newGeneratedId = rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
            logger.log("");
        }

        return newGeneratedId;
    }

    @Override
    public List<Transaction> findAll() {
        LinkedList<Transaction> transactions = new LinkedList<>();
        ResultSet resultSet = null;

        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(SQL_FIND_ALL_TRANSACTIONS);

            while (resultSet.next()) {

                int id = resultSet.getInt(1);

                LocalDateTime time_stamp = resultSet.getTimestamp(2).toLocalDateTime();
                double amount = resultSet.getDouble(3);

                int senderAccountId = resultSet.getInt(4);
                AccountDao accountDao = new AccountDao(logger);
                Account senderAccount = accountDao.findById(senderAccountId);

                int recipientAccountId = resultSet.getInt(5);
                Account recipientAccount = accountDao.findById(recipientAccountId);

                Transaction transaction = new Transaction(id, time_stamp, amount, senderAccount, recipientAccount);

                transactions.add(transaction);
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

        return transactions;
    }

    @Override
    public Transaction findById(int id) {
        Transaction transaction = null;
        ResultSet resultSet = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_TRANSACTION_BY_ID)) {

            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                id = resultSet.getInt(1);

                LocalDateTime time_stamp = resultSet.getTimestamp(2).toLocalDateTime();

                double amount = resultSet.getDouble(3);

                int senderAccountId = resultSet.getInt(4);
                AccountDao accountDao = new AccountDao(logger);
                Account senderAccount = accountDao.findById(senderAccountId);

                int recipientAccountId = resultSet.getInt(5);
                Account recipientAccount = accountDao.findById(recipientAccountId);

                transaction = new Transaction(id, time_stamp, amount, senderAccount, recipientAccount);
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

        return transaction;
    }
    //todo Ask: about unused methods from interface
    @Override
    public Transaction findByName(String name) {
        logger.log("Method 'TransactionDao.findByName(String name)' is not for use. " +
                "Transaction haven't name.");
        return null;
    }

    @Override
    public Transaction findBy(String columnName, String value) {
        logger.log("Method 'TransactionDao.findBy(String columnName, String value)' is not for use. " +
                "Transaction have no other parameters to find.");
        return null;
    }

    @Override
    public boolean update(Transaction entity) {
        logger.log("Method 'TransactionDao.update(Transaction entity)' is not for use. " +
                "Transaction cannot be changed.");
        return false;
    }

    @Override
    public boolean delete(Transaction entity) {
        logger.log("Method 'TransactionDao.update(Transaction entity)' is not for use. " +
                "Transaction can't delete.");

        return false;
    }

    public LinkedList<Transaction> findByAccountId(int accountId) {
        LinkedList<Transaction> transactions = new LinkedList<>();
        ResultSet resultSet = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_TRANSACTIONS_BY_ACCOUNT_ID)) {

            statement.setInt(1, accountId);
            statement.setInt(2, accountId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);

                LocalDateTime time_stamp = resultSet.getTimestamp(2).toLocalDateTime();
                double amount = resultSet.getDouble(3);

                int senderAccountId = resultSet.getInt(4);
                AccountDao accountDao = new AccountDao(logger);
                Account senderAccount = accountDao.findById(senderAccountId);

                int recipientAccountId = resultSet.getInt(5);
                Account recipientAccount = accountDao.findById(recipientAccountId);

                Transaction transaction = new Transaction(id, time_stamp, amount, senderAccount, recipientAccount);

                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

}
