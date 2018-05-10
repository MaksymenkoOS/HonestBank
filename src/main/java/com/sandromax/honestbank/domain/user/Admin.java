package com.sandromax.honestbank.domain.user;

/**
 * Created by sandro on 26.04.18.
 */
public class Admin {
    private int IdInDb;
    private String email;
    private String pass;


    public Admin() {
    }

    public Admin(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public Admin(int idInDb, String email, String pass) {
        IdInDb = idInDb;
        this.email = email;
        this.pass = pass;
    }


    public int getIdInDb() {
        return IdInDb;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public void setIdInDb(int idInDb) {
        IdInDb = idInDb;
    }

    public boolean clearPass() {
        pass = "";
        return true;
    }
}
