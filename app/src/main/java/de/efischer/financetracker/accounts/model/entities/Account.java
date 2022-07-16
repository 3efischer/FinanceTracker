package de.efischer.financetracker.accounts.model.entities;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.efischer.financetracker.accounts.model.valueobjects.AccountType;
import de.efischer.financetracker.accounts.model.valueobjects.Amount;

/**
 * Model for accounts of all types
 *
 * @author Evelyn Fischer
 */

@Entity
public class Account implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "account_type")
    private AccountType type;

    @Embedded
    private Amount balance;

    @ColumnInfo(name = "last_changed")
    private Date lastChanged;

    @ColumnInfo(name = "bank_name")
    private String bankName;

    @ColumnInfo(name = "sort_order")
    private int sortOrder;

    public Account() {
    }

    public Account(String name, AccountType type, Amount initialBalance) {
        this.name = name;
        this.type = type;
        this.balance = initialBalance;
        this.lastChanged = new Date();
    }

    public Account(String name, AccountType type, Amount initialBalance, String bankName) {
        this(name, type, initialBalance);
        this.bankName = bankName;
    }

    public Account(int id, String name, AccountType type, Amount initialBalance, String bankName) {
        this(name, type, initialBalance, bankName);
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
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
