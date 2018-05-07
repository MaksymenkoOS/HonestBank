package com.sandromax.honestbank.domain.account;

import com.sandromax.honestbank.domain.user.User;

import java.time.LocalDate;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedList;

/**
 * Created by sandro on 26.04.18.
 */
public class Account {
    private int idInDb;

    private String type;

    private User user;
    private double balance;

    private LocalDate validityFrom;
    private LocalDate validityTo;

    private double rate;

    private LinkedList<Transaction> history;


    /**
     * Constructors
     */
    public Account() {
    }

    /**
     * Initialize without idInDb and history
     * @param type  account type name
     * @param user  owner
     * @param balance   current balance
     * @param validityFrom  date and time of account opening
     * @param validityTo    end date and time
     * @param rate  credit or deposit rate
     */
    public Account(String type, User user, double balance, LocalDate validityFrom, LocalDate validityTo, double rate) {
        this.type = type;
        this.user = user;
        this.balance = balance;
        this.validityFrom = validityFrom;
        this.validityTo = validityTo;
        this.rate = rate;
    }

    /**
     * Initialize all fields
     * @param idInDb    id in the datebase
     * @param type  account type name
     * @param user  owner
     * @param balance   current balance
     * @param validityFrom  date and time of account opening
     * @param validityTo    end date and time
     * @param rate  credit or deposit rate
     * @param history   list of all transactions
     */
    public Account(int idInDb, String type, User user, double balance, LocalDate validityFrom, LocalDate validityTo, double rate, LinkedList<Transaction> history) {
        this.idInDb = idInDb;
        this.type = type;
        this.user = user;
        this.balance = balance;
        this.validityFrom = validityFrom;
        this.validityTo = validityTo;
        this.rate = rate;
        this.history = history;
    }

    /**
     * Getters
     */
    /**
     * Getter id
     * @return  id in the datebase
     */
    public int getIdInDb() {
        return idInDb;
    }
    /**
     * Getter type
     * @return  account type name
     */
    public String getType() {
        return type;
    }

    /**
     * Getter user
     * @return  owner
     */
    public User getUser() {
        return user;
    }

    /**
     * Getter balance
     * @return  current balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Getter validityFrom
     * @return  date and time of account opening
     */
    public LocalDate getValidityFrom() {
        return validityFrom;
    }

    /**
     * Getter validityTo
     * @return  end date and time
     */
    public LocalDate getValidityTo() {
        return validityTo;
    }

    /**
     * Getter rate
     * @return  credit or deposit rate
     */
    public double getRate() {
        return rate;
    }

    /**
     * Getter history
     * @return  list of all transactions
     */
    public LinkedList<Transaction> getHistory() {
        return history;
    }

    /**
     * Setters
     */

    /**
     * Setter balance
     * @param balance   to change balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Setter validityTo
     * @param validityTo    for prolongation
     */
    public void setValidityTo(LocalDate validityTo) {
        this.validityTo = validityTo;
    }

    /**
     * Setter history
     * @param history   for add new transactions
     */
    public void setHistory(LinkedList<Transaction> history) {
        this.history = history;
    }
}
