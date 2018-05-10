package com.sandromax.honestbank.domain.user;

/**
 * Created by sandro on 26.04.18.
 *
 * Entity of the client of the bank
 *
 * @author Maksymenko Oleksandr
 */
public class User {
    private int idInDb;
    private String name;
    private String email;
    private String pass;

    /**
     * Constructor without arguments
     */
    public User() {
    }

    /**
     * Constructor fills all fields except one (id in the database)
     * @param name  user name
     * @param email user email
     * @param pass  user password
     */
    public User(String name, String email, String pass) {
        this.name = name;
        this.email = email;
        this.pass = pass;
    }

    /**
     * Initializes a newly created object. Fills all fields.
     * @param idInDb    id in the database
     * @param name  user name
     * @param email user email
     * @param pass  user password
     */
    public User(int idInDb, String name, String email, String pass) {
        this.idInDb = idInDb;
        this.name = name;
        this.email = email;
        this.pass = pass;
    }

    /**
     * Safe Initialize (without password)
     * @param idInDb    id in the database
     * @param name  user name
     * @param email user email
     */
    public User(int idInDb, String name, String email) {
        this.idInDb = idInDb;
        this.name = name;
        this.email = email;
    }

    /**
     * Getter
     * @return  id in the database
     */
    public int getIdInDb() {
        return idInDb;
    }

    /**
     * Getter
     * @return user name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter
     * @return  user email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter
     * @return  user password
     */
    public String getPass() {
        return pass;
    }

    /**
     * Setter
     * @param idInDb    id in the database
     */
    public void setIdInDb(int idInDb) {
        this.idInDb = idInDb;
    }

    public boolean clearPass() {
        pass = "";
        return true;
    }
}
