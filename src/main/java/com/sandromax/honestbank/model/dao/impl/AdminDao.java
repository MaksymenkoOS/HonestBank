package com.sandromax.honestbank.model.dao.impl;

import com.sandromax.honestbank.domain.user.Admin;
import com.sandromax.honestbank.model.dao.GenericDao;
import com.sandromax.honestbank.model.dao.connection.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;

public class AdminDao implements GenericDao<Admin> {

    private static final Logger logger = Logger.getLogger(AdminDao.class.getName());

    private static final String SQL_CREATE_ADMIN = "INSERT INTO admin (email, pass) VALUES(?, ?);";
    private static final String SQL_FIND_ALL_ADMINS = "SELECT * FROM admin;";
    private static final String SQL_FIND_ADMIN_BY_ID = "SELECT * FROM admin WHERE id = ?;";
//    private static final String SQL_FIND_ADMIN_BY_NAME = "SELECT * FROM admin WHERE name = ?;";
    private static final String SQL_UPDATE_ADMIN = "UPDATE admin SET pass = ? WHERE email = ?;";
    private static final String SQL_DELETE_ADMIN = "DELETE FROM admin WHERE email = ?;";
    private static final String SQL_FIND_ADMIN_BY_EMAIL = "SELECT * FROM admin WHERE email = ?;";

    @Override
    public int create(Admin entity) {
        int newGeneratedId = 0;
        ResultSet rs = null;

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_CREATE_ADMIN, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, entity.getEmail());
            statement.setString(2, entity.getPass());

            statement.executeUpdate();

            rs = statement.getGeneratedKeys();
            while (rs.next())
                newGeneratedId = rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        logger.trace("admin was registered with id:" + newGeneratedId);
        return newGeneratedId;
    }

    @Override
    public LinkedList<Admin> findAll() {
        LinkedList<Admin> admins = new LinkedList<>();
        ResultSet resultSet = null;

        try(Connection connection = ConnectionPool.getConnection();
            Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(SQL_FIND_ALL_ADMINS);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String email = resultSet.getString(2);
                String pass = resultSet.getString(3);

                Admin admin = new Admin(id, email, pass);
                admins.add(admin);
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
        logger.trace("Number of all admins: " + admins.size());
        return admins;
    }

    @Override
    public Admin findById(int id) {
        Admin admin = null;
        ResultSet resultSet = null;

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ADMIN_BY_ID)) {

            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
//                int id = resultSet.getInt(1);
                String email = resultSet.getString(2);
                String passDb = resultSet.getString(3);

                admin = new Admin(id, email, passDb);
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
        logger.trace("find " + admin.getEmail() + " by " + "id: " + admin.getIdInDb());
        return admin;
    }

//    @Override
//    public Admin findBy(String columnName, String value) {
//        return null;
//    }

//    @Override
//    public Admin findByName(String name) {
//        return null;
//    }

    @Override
    public boolean update(Admin entity) {
        boolean result = false;

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ADMIN)) {

            statement.setString(1, entity.getPass());
            statement.setString(2, entity.getEmail());

            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        logger.trace("Password was changed.");
        return result;
    }

    @Override
    public boolean delete(Admin entity) {
        boolean result = false;

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ADMIN)) {

            statement.setString(1, entity.getEmail());

            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        logger.trace("admin: " + entity.getEmail() + " was deleted");
        return result;
    }

    public Admin findByEmail(String email) {
        Admin admin = null;
        ResultSet resultSet = null;

        try(Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ADMIN_BY_EMAIL)) {

            statement.setString(1, email);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
//                String emailDb = resultSet.getString(2);
                String passDb = resultSet.getString(3);

                admin = new Admin(id, email, passDb);
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

        logger.trace("Found " + admin.getEmail() + " by email: " + email);
        return admin;
    }
}
