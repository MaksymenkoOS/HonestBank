package com.sandromax.honestbank.domain.user;

/**
 * Created by sandro on 26.04.18.
 */
public class Admin {
    private int IdInDb;
    private String email;


    public Admin() {
    }

    public Admin(String email) {
        this.email = email;
    }

    public Admin(int idInDb, String email) {
        IdInDb = idInDb;
        this.email = email;
    }


    public int getIdInDb() {
        return IdInDb;
    }

    public String getEmail() {
        return email;
    }

    public void setIdInDb(int idInDb) {
        IdInDb = idInDb;
    }
}
