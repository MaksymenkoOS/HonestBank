package com.sandromax.honestbank.domain.account;

import com.sandromax.honestbank.domain.user.User;

import java.time.LocalDate;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedList;

/**
 * Created by sandro on 26.04.18.
 */
public class Account {
    private User user;

    private double balance;
    private LocalDate validityFrom;
    private LocalDate validityTo;

}
