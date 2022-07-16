package de.efischer.financetracker.transactions.model.entities;

import java.util.Date;

import de.efischer.financetracker.accounts.model.entities.Account;
import de.efischer.financetracker.accounts.model.valueobjects.Amount;

public class Transaction {

    private Account fromAccount;
    private Amount amount;
    private Category category;
    private Date date;
    private String note;

    public Transaction(Account fromAccount, Amount amount, Category category, Date date, String note) {
        this.fromAccount = fromAccount;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.note = note;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
