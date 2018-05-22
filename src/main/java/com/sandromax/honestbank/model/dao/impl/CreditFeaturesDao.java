package com.sandromax.honestbank.model.dao.impl;

import com.sandromax.honestbank.domain.account.CreditFeatures;
import com.sandromax.honestbank.model.dao.GenericDao;
import com.sandromax.honestbank.model.dao.connection.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;

public class CreditFeaturesDao implements GenericDao<CreditFeatures> {

    private static final Logger logger = Logger.getLogger(CreditFeaturesDao.class.getName());

    private static final String SQL_CREATE_CREDIT_FEATURES = "INSERT INTO credit_features (account_id, credit_limit, indebtedness) VALUES(?, ?, ?);";
    private static final String SQL_FIND_ALL_CREDIT_FEATURES = "SELECT * FROM credit_features;";
    private static final String SQL_FIND_CREDIT_FEATURES_BY_ID = "SELECT * FROM credit_features WHERE account_id = ?;";

    private static final String SQL_UPDATE_CREDIT_FEATURES = "UPDATE credit_features SET account_id = ?, credit_limit ?, indebtedness = ? WHERE account_id = ?;";
    private static final String SQL_DELETE_CREDIT_FEATURES = "DELETE FROM credit_features WHERE account_id = ?;";



    @Override
    public int create(CreditFeatures entity) {
        int generatedId = 0;

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_CREATE_CREDIT_FEATURES)) {

            statement.setInt(1, entity.getAccountId());
            statement.setDouble(2, entity.getCreditLimit());
            statement.setDouble(3, entity.getIndebtedness());

            statement.execute();

            generatedId = entity.getAccountId();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedId;
    }

    @Override
    public LinkedList<CreditFeatures> findAll() {
        LinkedList<CreditFeatures> features = new LinkedList<>();
        ResultSet resultSet = null;

        try(Connection connection = ConnectionPool.getConnection();
            Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(SQL_FIND_ALL_CREDIT_FEATURES);

            while (resultSet.next()) {
                int accountId = resultSet.getInt(1);
                double creditLimit = resultSet.getDouble(2);
                double accruedInterest = resultSet.getDouble(3);

                CreditFeatures feature = new CreditFeatures(accountId, creditLimit, accruedInterest);
                features.add(feature);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return features;
    }

    @Override
    public CreditFeatures findById(int id) {
        CreditFeatures features = null;
        ResultSet resultSet = null;

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_CREDIT_FEATURES_BY_ID)) {

            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int accountId = resultSet.getInt(1);
                double creditLimit = resultSet.getDouble(2);
                double accruedInterest = resultSet.getDouble(3);

                features = new CreditFeatures(accountId, creditLimit, accruedInterest);
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

        return features;
    }

    @Override
    public boolean update(CreditFeatures entity) {
        boolean result = false;

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_CREDIT_FEATURES)) {

            statement.setInt(1, entity.getAccountId());
            statement.setDouble(2, entity.getCreditLimit());
            statement.setDouble(3, entity.getIndebtedness());

            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean delete(CreditFeatures entity) {
        boolean result = false;

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_CREDIT_FEATURES)) {

            statement.setInt(1, entity.getAccountId());

            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
