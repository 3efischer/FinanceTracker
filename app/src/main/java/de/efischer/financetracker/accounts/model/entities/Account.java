package de.efischer.financetracker.accounts.model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.efischer.financetracker.accounts.model.valueobjects.AccountType;
import de.efischer.financetracker.accounts.model.valueobjects.Amount;

/**
 * Model for accounts of all types
 *
 * @author Evelyn Fischer
 */

public class Account {

    private String name;
    private AccountType type;
    private Amount balance;
    private Date lastChanged;
    private int sortOrder;

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

    public Amount getBalance() {
        return balance;
    }

    public void setBalance(Amount balance) {
        this.balance = balance;
    }

    public Date getLastChanged() {
        return lastChanged;
    }

    public void setLastChanged(Date lastChanged) {
        this.lastChanged = lastChanged;
    }

    public String getLastDayChanged() {
        return SimpleDateFormat.getDateInstance().format(lastChanged);
    }

    public String getLastTimeChanged() {
        return SimpleDateFormat.getTimeInstance().format(lastChanged);
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }
}
