package com.sandromax.honestbank.domain.user;

/**
 * Created by sandro on 26.04.18.
 */
public class User {
    private int idInDb;
    private String name;
    private String email;


    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User(int idInDb, String name, String email) {
        this.idInDb = idInDb;
        this.name = name;
        this.email = email;
    }


    public int getIdInDb() {
        return idInDb;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setIdInDb(int idInDb) {
        this.idInDb = idInDb;
    }
}
