package com.sandromax.honestbank.model.dao.impl;

import com.sandromax.honestbank.domain.service.BCrypt;
import com.sandromax.honestbank.domain.user.User;
import com.sandromax.honestbank.model.dao.GenericDao;
import com.sandromax.honestbank.model.dao.connection.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao implements GenericDao<User> {

    private static final String SQL_INSERT_USER = "INSERT INTO themes(name, email, pass) VALUES(?, ?, ?);";

    @Override
    public boolean create(User entity) {

        boolean result = false;
        String passHash = BCrypt.hashpw(entity.getPass(), BCrypt.gensalt(13));

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER)) {

            statement.setString(1, entity.getName());
            statement.setString(2, entity.getEmail());
            statement.setString(1, passHash);

            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public User findByName(String name) {
        return null;
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    private static final String SQL_FIND_BY_EMAIL = "SELECT * FROM user WHERE email = ?";

    public User findByEmail(String email) {
        User user = null;
        ResultSet resultSet = null;

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_EMAIL)) {

            statement.setString(1, email);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getNString(2);
//                String emailDb = resultSet.getString(3);
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

        return user;
    }
}
