package com.sandromax.honestbank.model.dao.impl;

import com.sandromax.honestbank.domain.account.AccountType;
import com.sandromax.honestbank.domain.service.log.Logger;
import com.sandromax.honestbank.model.dao.GenericDao;
import com.sandromax.honestbank.model.dao.connection.ConnectionPool;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class AccountTypeDao implements GenericDao<AccountType> {

    public AccountTypeDao(Logger logger) {
        this.logger = logger;
    }

    private Logger logger;

    private static final String SQL_CREATE_ACCOUNT_TYPE = "INSERT INTO account_type (name) VALUES(?);";
    private static final String SQL_FIND_ALL_ACCOUNT_TYPES = "SELECT * FROM account_type;";
    private static final String SQL_FIND_ACCOUNT_TYPE_BY_ID = "SELECT name FROM account_type WHERE id = ?;";
    private static final String SQL_FIND_ACCOUNT_TYPE_BY_NAME = "SELECT name FROM account_type WHERE name = ?;";
    private static final String SQL_UPDATE_ACCOUNT_TYPE = "UPDATE account_type SET name = ? WHERE id = ?;";
    private static final String SQL_DELETE_ACCOUNT_TYPE = "DELETE FROM account_type name = ?;";
    private static final String SQL_FIND_ID_BY_NAME = "SELECT id FROM account_type WHERE name = ?;";


    @Override
    public int create(AccountType entity) {
        int newGeneratedId = 0;

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_CREATE_ACCOUNT_TYPE)) {

            //  lower case or upper case
            //  which is better ?
            statement.setString(1, entity.name());

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
    public List<AccountType> findAll() {
        List<AccountType> types = new LinkedList<>();
        ResultSet resultSet = null;

        try(Connection connection = ConnectionPool.getConnection();
            Statement statement = connection.createStatement()) {

            resultSet = statement.executeQuery(SQL_FIND_ALL_ACCOUNT_TYPES);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                AccountType type = AccountType.valueOf(name);

                types.add(type);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return types;    }

    @Override
    public AccountType findById(int id) {
        AccountType type = null;
        ResultSet resultSet = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ACCOUNT_TYPE_BY_ID)) {

            statement.setInt(1, id);
            statement.executeQuery();

            while (resultSet.next()) {
                type = AccountType.valueOf(resultSet.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return type;
    }

        @Override
    public AccountType findByName(String name) {
            AccountType type = null;
            ResultSet resultSet = null;

            try (Connection connection = ConnectionPool.getConnection();
                 PreparedStatement statement = connection.prepareStatement(SQL_FIND_ACCOUNT_TYPE_BY_NAME)) {

                statement.setString(1, name);
                statement.executeQuery();

                while (resultSet.next()) {
                    type = AccountType.valueOf(resultSet.getString(1));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return type;
    }

    @Override
    public AccountType findBy(String columnName, String value) {
        return null;
    }

    @Override
    public boolean update(AccountType entity) {
        boolean result = false;
        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ACCOUNT_TYPE)) {

            statement.setString(1, entity.name());

            int id = findIdByName(entity);

            if(id == 0)
                return result;

            statement.setInt(2, id);

            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean delete(AccountType entity) {
        boolean result = false;

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ACCOUNT_TYPE)) {

            statement.setString(1, entity.name());

            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int findIdByName(AccountType entity) {
        int id = 0;
        ResultSet resultSet = null;

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ID_BY_NAME)) {

            statement.setString(1, entity.name());
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }
}
