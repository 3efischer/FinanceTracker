package de.efischer.financetracker.accounts.model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.efischer.financetracker.accounts.model.valueobjects.AccountType;
import de.efischer.financetracker.accounts.model.valueobjects.Amount;

/**
 * Model for accounts of all types
 * @author Evelyn Fischer
 */

public class Account {

    private String name;
    private AccountType type;
    private Amount balance;
    private Date lastChanged;

    public Account(String name, AccountType type, Amount initialBalance) {
        this.name = name;
        this.type = type;
        this.balance = initialBalance;
        this.lastChanged = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public String getLastDayChanged() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("dd.MM.yy");

        return simpleDateFormat.format(lastChanged);
    }

    public String getLastTimeChanged() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("HH:mm");

        return simpleDateFormat.format(lastChanged);
    }

    public void setLastChanged(Date lastChanged) {
        this.lastChanged = lastChanged;
    }

    public Amount getBalance() {
        return balance;
    }
}