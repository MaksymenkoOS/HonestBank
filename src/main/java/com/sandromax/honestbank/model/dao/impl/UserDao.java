package com.sandromax.honestbank.model.dao.impl;

import com.sandromax.honestbank.domain.service.BCrypt;
import com.sandromax.honestbank.domain.user.User;
import com.sandromax.honestbank.model.dao.GenericDao;
import com.sandromax.honestbank.model.dao.connection.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDao implements GenericDao<User> {

    private static final Logger logger = Logger.getLogger(UserDao.class.getName());

    private static final String SQL_CREATE_USER = "INSERT INTO user(name, email, pass) VALUES(?, ?, ?);";
    private static final String SQL_FIND_ALL_USERS = "SELECT * FROM user;";
    private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM user WHERE id = ?;";
    private static final String SQL_FIND_USER_BY_NAME = "SLECT * FROM user WHERE name = ?;";
    private static final String SQL_FIND_USER_BY = "SELECT * FROM user WHERE ? = ?";
    private static final String SQL_UPDATE_USER = "UPDATE user SET name = ?, pass = ? WHERE email = ?;";
    private static final String SQL_DELETE_USER = "DELETE FROM user WHERE email = ?;";
    private static final String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM user WHERE email = ?";
    private static final String SQL_FIND_USER_BY_EMAIL_AND_PASS = "SELECT * FROM user WHERE email = ? AND pass = ?";



    @Override
    public int create(User entity) {

        int newGeneratedId = 0;

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_CREATE_USER, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, entity.getName());
            statement.setString(2, entity.getEmail());
            statement.setString(3, entity.getPass());

            statement.executeUpdate();

            //  return new new generated id
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                newGeneratedId = rs.getInt(1);
                logger.trace("user: " + entity.getName() + " was successfully registered");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        logger.trace("Registered with id: " + newGeneratedId);
        return newGeneratedId;
    }

    @Override
    public LinkedList<User> findAll() {
        LinkedList<User> users = new LinkedList<>();
        ResultSet resultSet = null;

        try(Connection connection = ConnectionPool.getConnection();
            Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(SQL_FIND_ALL_USERS);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                String pass = resultSet.getString(4);

                User user = new User(id, name, email, pass);
                users.add(user);
            }

            logger.trace("found " + users.size() + " users.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }


    @Override
    public User findById(int id) {
        User user = null;
        ResultSet resultSet = null;

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {

            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
//                int id = resultSet.getInt(1);
                String name = resultSet.getNString(2);
                String email = resultSet.getString(3);
                String passDb = resultSet.getString(4);

                user = new User(id, name, email, passDb);
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

        logger.trace("Found " + user.getEmail() + " by id: " + id);
        return user;
    }

//    @Override
    public User findByName(String name) {
        User user = null;
        ResultSet resultSet = null;

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_NAME)) {

            statement.setString(1, name);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
//                String name = resultSet.getNString(2);
                String email = resultSet.getString(3);
                String passDb = resultSet.getString(4);

                user = new User(id, name, email, passDb);
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

        logger.trace("Found " + user.getEmail() + " by name: " + name);
        return user;
    }

    @Override
    public boolean update(User entity) {
        boolean result = false;

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER)) {

            statement.setString(1, entity.getName());
            statement.setString(2, entity.getPass());
            statement.setString(3, entity.getEmail());

            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        logger.trace("Found " + entity.getEmail() + " was updated");
        return result;
    }

    @Override
    public boolean delete(User entity) {
        boolean result = false;

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER)) {

            statement.setString(1, entity.getEmail());

            result = statement.execute();

            if(result)
                logger.trace("user " + entity.getName() + " was deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public User findByEmail(String email) {
        User user = null;
        ResultSet resultSet = null;

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_EMAIL)) {

            statement.setString(1, email);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getNString(2);
                String passDb = resultSet.getString(4);

                user = new User(id, name, email, passDb);
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

        logger.trace("Found " + user.getName() + " by email: " + email);
        return user;
    }

}
