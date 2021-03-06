package de.efischer.financetracker.accounts.model.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Objects;

import de.efischer.financetracker.accounts.model.valueobjects.AccountType;
import de.efischer.financetracker.accounts.model.valueobjects.Amount;

/**
 * Model for accounts of all types
 *
 * @author Evelyn Fischer
 */

@Entity(tableName = "account")
public class Account implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    @ColumnInfo(name = "account_type")
    private AccountType type;

    @Embedded
    @NonNull
    private Amount balance;

    @ColumnInfo(name = "last_changed")
    @NonNull
    private Date lastChanged;

    @ColumnInfo(name = "bank_name")
    private String bankName;

    @ColumnInfo(name = "list_position")
    private int listPosition;


    public Account() {
        this.name = "";
        this.type = AccountType.CASH;
        this.balance = Amount.of(BigDecimal.ZERO, Currency.getInstance("EUR"));
        this.lastChanged = new Date();
    }


    public Account(@NonNull String name, @NonNull AccountType type, @NonNull Amount initialBalance) {
        this.name = name;
        this.type = type;
        this.balance = initialBalance;
        this.lastChanged = new Date();
    }

    public Account(@NonNull String name, @NonNull AccountType type, @NonNull Amount initialBalance, @NonNull String bankName) {
        this(name, type, initialBalance);
        this.bankName = bankName;
    }

    public Account(int id, @NonNull String name, @NonNull AccountType type, @NonNull Amount initialBalance, @NonNull String bankName) {
        this(name, type, initialBalance, bankName);
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public AccountType getType() {
        return type;
    }

    public void setType(@NonNull AccountType type) {
        this.type = type;
    }

    @NonNull
    public String getBankName() {
        return bankName;
    }

    public void setBankName(@NonNull String bankName) {
        this.bankName = bankName;
    }

    @NonNull
    public Amount getBalance() {
        return balance;
    }

    public void setBalance(@NonNull Amount balance) {
        this.balance = balance;
    }

    @NonNull
    public Date getLastChanged() {
        return lastChanged;
    }

    public void setLastChanged(@NonNull Date lastChanged) {
        this.lastChanged = lastChanged;
    }

    @NonNull
    public String getLastDayChanged() {
        return SimpleDateFormat.getDateInstance().format(lastChanged);
    }

    @NonNull
    public String getLastTimeChanged() {
        return SimpleDateFormat.getTimeInstance().format(lastChanged);
    }

    public int getListPosition() {
        return listPosition;
    }

    public void setListPosition(int listPosition) {
        this.listPosition = listPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && listPosition == account.listPosition && name.equals(account.name) && type == account.type && balance.equals(account.balance) && lastChanged.equals(account.lastChanged) && Objects.equals(bankName, account.bankName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, balance, lastChanged, bankName, listPosition);
    }
}
