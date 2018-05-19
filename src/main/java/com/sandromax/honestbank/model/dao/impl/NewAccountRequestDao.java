package com.sandromax.honestbank.model.dao.impl;

import com.sandromax.honestbank.domain.account.AccountType;
import com.sandromax.honestbank.domain.account.NewAccountRequest;
import com.sandromax.honestbank.domain.service.log.Logger;
import com.sandromax.honestbank.domain.user.User;
import com.sandromax.honestbank.model.dao.GenericDao;
import com.sandromax.honestbank.model.dao.connection.ConnectionPool;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;

public class NewAccountRequestDao implements GenericDao<NewAccountRequest> {

    public NewAccountRequestDao(Logger logger) {
        this.logger = logger;
    }

    private Logger logger;

    private static final String SQL_CREATE_NEW_ACCOUNT_REQUEST = "INSERT INTO new_account_request(type_id, user_id, date, is_accepted, is_declined, rate, credit_limit) VALUES(?, ?, ?, false, false, ?, ?);";
    private static final String SQL_FIND_ALL_ACCOUNT_REQUESTS = "SELECT * FROM new_account_request;";
    private static final String SQL_FIND_ACCOUNT_REQUEST_BY_ID = "SELECT * FROM new_account_request WHERE id = ?;";
    private static final String SQL_FIND_ALL_ACCOUNT_REQUESTS_NOT_CONFIRMED = "SELECT * FROM new_account_request WHERE is_accepted = false AND is_declined = false;";
    private static final String SQL_FIND_ACCOUNT_REQUEST_BY_TYPE_ID_AND_USER_ID_NOT_CONFIRMED = "SELECT id FROM new_account_request WHERE type_id = ? AND user_id = ? AND is_accepted = false AND is_declined = false;";
    private static final String SQL_SET_ACCEPTED_BY_ID = "UPDATE new_account_request SET is_accepted = true WHERE id = ?;";
    private static final String SQL_SET_DECLINED_BY_ID = "UPDATE new_account_request SET is_declined = true WHERE id = ?;";
    private static final String SQL_FIND_ALL_DECLINED = "SELECT * FROM new_account_request WHERE is_accepted = false AND is_declined = true;";

    @Override
    public int create(NewAccountRequest entity) {
        int newGeneratedId = 0;

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_CREATE_NEW_ACCOUNT_REQUEST, Statement.RETURN_GENERATED_KEYS)) {

            AccountTypeDao accountTypeDao = new AccountTypeDao(logger);
            AccountType type = entity.getAccountType();
            int typeId = accountTypeDao.findId(type);

            int userId = entity.getUser().getIdInDb();
            if(userId == 0) {
                UserDao userDao = new UserDao(logger);
                String email = entity.getUser().getEmail();
                User user = userDao.findByEmail(email);
                userId = user.getIdInDb();
            }

            statement.setInt(1, typeId);
            statement.setInt(2, userId);
            statement.setDate(3, Date.valueOf(LocalDate.now()));
            statement.setDouble(4, entity.getRate());
            statement.setDouble(5, entity.getLimit());

            statement.executeUpdate();

            //  return new new generated id
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next())
                newGeneratedId = rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return newGeneratedId;
    }

    @Override
    public LinkedList<NewAccountRequest> findAll() {
        LinkedList<NewAccountRequest> requests = new LinkedList<>();
        ResultSet resultSet = null;

        try(Connection connection = ConnectionPool.getConnection();
            Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(SQL_FIND_ALL_ACCOUNT_REQUESTS);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int typeId = resultSet.getInt(2);
                int userId = resultSet.getInt(3);
                LocalDate date = resultSet.getDate(4).toLocalDate();
                boolean isAccepted = resultSet.getBoolean(5);
                boolean isDeclined = resultSet.getBoolean(6);
                double rate = resultSet.getDouble(7);
                double limit = resultSet.getDouble(8);

                AccountTypeDao accountTypeDao = new AccountTypeDao(logger);
                AccountType accountType = accountTypeDao.findById(typeId);

                UserDao userDao = new UserDao(logger);
                User user = userDao.findById(userId);

                NewAccountRequest newAccountRequest = new NewAccountRequest(id, accountType, user, date, isAccepted, isDeclined, rate, limit);
                requests.add(newAccountRequest);
            }

            logger.log("found " + requests.size() + " requests.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return requests;
    }

    @Override
    public NewAccountRequest findById(int id) {
        NewAccountRequest newAccountRequest = null;
        ResultSet resultSet = null;

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ACCOUNT_REQUEST_BY_ID)) {

            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
//                int id = resultSet.getInt(1);
                int typeId = resultSet.getInt(2);
                int userId = resultSet.getInt(3);
                LocalDate date = resultSet.getDate(4).toLocalDate();
                boolean isAccepted = resultSet.getBoolean(5);
                boolean isDeclined = resultSet.getBoolean(6);
                double rate = resultSet.getDouble(7);
                double limit = resultSet.getDouble(8);

                AccountTypeDao accountTypeDao = new AccountTypeDao(logger);
                AccountType accountType = accountTypeDao.findById(typeId);

                UserDao userDao = new UserDao(logger);
                User user = userDao.findById(userId);

                newAccountRequest = new NewAccountRequest(id, accountType, user, date, isAccepted, isDeclined, rate, limit);
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

        return newAccountRequest;
    }

    @Override
    public boolean update(NewAccountRequest entity) {
        logger.log("can't change NewAccountRequest");
        return false;
    }

    @Override
    public boolean delete(NewAccountRequest entity) {
        logger.log("can't change and delete NewAccountRequest");
        return false;
    }

    public LinkedList<NewAccountRequest> findAllNotConfirmed() {
        LinkedList<NewAccountRequest> requests = new LinkedList<>();
        ResultSet resultSet = null;

        try(Connection connection = ConnectionPool.getConnection();
            Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(SQL_FIND_ALL_ACCOUNT_REQUESTS_NOT_CONFIRMED);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int typeId = resultSet.getInt(2);
                int userId = resultSet.getInt(3);
                LocalDate date = resultSet.getDate(4).toLocalDate();
                boolean isAccepted = resultSet.getBoolean(5);
                boolean isDeclined = resultSet.getBoolean(6);
                double rate = resultSet.getDouble(7);
                double limit = resultSet.getDouble(8);

                AccountTypeDao accountTypeDao = new AccountTypeDao(logger);
                AccountType accountType = accountTypeDao.findById(typeId);

                UserDao userDao = new UserDao(logger);
                User user = userDao.findById(userId);

                NewAccountRequest newAccountRequest = new NewAccountRequest(id, accountType, user, date, isAccepted, isDeclined, rate, limit);
                requests.add(newAccountRequest);
            }

            logger.log("found " + requests.size() + " requests.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return requests;
    }

    public boolean isContainNotConfirmedByUserAndType(AccountType type, User user) {
        ResultSet resultSet = null;

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ACCOUNT_REQUEST_BY_TYPE_ID_AND_USER_ID_NOT_CONFIRMED)) {

            AccountTypeDao accountTypeDao = new AccountTypeDao(logger);
            int typeId = accountTypeDao.findId(type);

            int userId = user.getIdInDb();
            if(userId == 0) {
                UserDao userDao = new UserDao(logger);
                String email = user.getEmail();
                User userDb = userDao.findByEmail(email);
                userId = userDb.getIdInDb();
            }

            statement.setInt(1, typeId);
            statement.setInt(2, userId);

            resultSet = statement.executeQuery();
            if(resultSet.next())
                return true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //  if can't check - can't add!
        return true;
    }

    public boolean setConfirmedById(int id) {

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SET_ACCEPTED_BY_ID)) {

            statement.setInt(1, id);

            return statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean setDeclinedById(int id) {
        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SET_DECLINED_BY_ID)) {

            statement.setInt(1, id);

            return statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public LinkedList<NewAccountRequest> findAllDeclined() {
        LinkedList<NewAccountRequest> requests = new LinkedList<>();
        ResultSet resultSet = null;

        try(Connection connection = ConnectionPool.getConnection();
            Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(SQL_FIND_ALL_DECLINED);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int typeId = resultSet.getInt(2);
                int userId = resultSet.getInt(3);
                LocalDate date = resultSet.getDate(4).toLocalDate();
                boolean isAccepted = resultSet.getBoolean(5);
                boolean isDeclined = resultSet.getBoolean(6);
                double rate = resultSet.getDouble(7);
                double limit = resultSet.getDouble(8);

                AccountTypeDao accountTypeDao = new AccountTypeDao(logger);
                AccountType accountType = accountTypeDao.findById(typeId);

                UserDao userDao = new UserDao(logger);
                User user = userDao.findById(userId);

                NewAccountRequest newAccountRequest = new NewAccountRequest(id, accountType, user, date, isAccepted, isDeclined, rate, limit);
                requests.add(newAccountRequest);
            }

            logger.log("found " + requests.size() + " requests.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return requests;
    }
}
