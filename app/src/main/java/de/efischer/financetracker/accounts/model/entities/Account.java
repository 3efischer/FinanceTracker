package de.efischer.financetracker.accounts.model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.efischer.financetracker.accounts.model.valueobjects.AccountType;
import de.efischer.financetracker.accounts.model.valueobjects.Amount;
import de.efischer.financetracker.accounts.model.valueobjects.CreditCardDetails;

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

    private String bankName;
    private CreditCardDetails creditCardDetails;

    public Account(String name, AccountType type, Amount initialBalance) {
        this.name = name;
        this.type = type;
        this.balance = initialBalance;
        this.lastChanged = new Date();
    }

    public String getName() {
        return name;
    }

    public AccountType getType() {
        return type;
    }

    public String getLastDayChanged() {
        return SimpleDateFormat.getDateInstance().format(lastChanged);
    }

    public String getLastTimeChanged() {
        return SimpleDateFormat.getTimeInstance().format(lastChanged);
    }

    public void setLastChanged(Date lastChanged) {
        this.lastChanged = lastChanged;
    }

    public Amount getBalance() {
        return balance;
    }
}
