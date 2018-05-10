package com.sandromax.honestbank.model.dao.impl;

import com.sandromax.honestbank.domain.account.AccountType;
import com.sandromax.honestbank.domain.account.NewAccountRequest;
import com.sandromax.honestbank.domain.service.log.Logger;
import com.sandromax.honestbank.domain.user.User;
import com.sandromax.honestbank.model.dao.GenericDao;
import com.sandromax.honestbank.model.dao.connection.ConnectionPool;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class NewAccountRequestDao implements GenericDao<NewAccountRequest> {

    public NewAccountRequestDao(Logger logger) {
        this.logger = logger;
    }

    private Logger logger;

    private static final String SQL_CREATE_NEW_ACCOUNT_REQUEST = "INSERT INTO new_account_request(type_id, user_id) VALUES(?, ?);";
    private static final String SQL_FIND_ALL_ACCOUNT_REQUESTS = "SELECT * FROM new_account_request;";
    private static final String SQL_FIND_ACCOUNT_REQUEST_BY_ID = "SELECT * FROM new_account_request WHERE id = ?;";
//    private static final String SQL_UPDATE_ACCOUNT_REQUEST = "UPDATE new_account_request SET name = ?, pass = ? WHERE email = ?;";
    private static final String SQL_DELETE_ACCOUNT_REQUEST = "DELETE FROM new_account_request WHERE id = ?;";

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
    public List<NewAccountRequest> findAll() {
        List<NewAccountRequest> requests = new LinkedList<>();
        ResultSet resultSet = null;

        try(Connection connection = ConnectionPool.getConnection();
            Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(SQL_FIND_ALL_ACCOUNT_REQUESTS);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int typeId = resultSet.getInt(2);
                int userId = resultSet.getInt(3);

                AccountTypeDao accountTypeDao = new AccountTypeDao(logger);
                AccountType accountType = accountTypeDao.findById(typeId);

                UserDao userDao = new UserDao(logger);
                User user = userDao.findById(userId);

                NewAccountRequest newAccountRequest = new NewAccountRequest(id, accountType, user);
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

                AccountTypeDao accountTypeDao = new AccountTypeDao(logger);
                AccountType accountType = accountTypeDao.findById(typeId);

                UserDao userDao = new UserDao(logger);
                User user = userDao.findById(userId);

                newAccountRequest = new NewAccountRequest(id, accountType, user);
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

//    @Override
//    public NewAccountRequest findByName(String name) {
//        return null;
//    }
//
//    @Override
//    public NewAccountRequest findBy(String columnName, String value) {
//        return null;
//    }

    @Override
    public boolean update(NewAccountRequest entity) {
        logger.log("better delete");
        return false;
    }

    @Override
    public boolean delete(NewAccountRequest entity) {
        boolean result = false;

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ACCOUNT_REQUEST)) {

            statement.setInt(1, entity.getIdInDb());

            result = statement.execute();

            if(result)
                logger.log("new account request of user (id: " + entity.getIdInDb() + ") was deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
