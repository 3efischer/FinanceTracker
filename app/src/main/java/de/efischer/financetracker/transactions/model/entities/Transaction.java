package de.efischer.financetracker.transactions.model.entities;

import java.util.Date;

import de.efischer.financetracker.accounts.model.entities.Account;
import de.efischer.financetracker.accounts.model.valueobjects.MonetaryAmount;

public class Transaction {

    private Account fromAccount;
    private MonetaryAmount monetaryAmount;
    private Category category;
    private Date date;
    private String note;

    public Transaction(Account fromAccount, MonetaryAmount monetaryAmount, Category category, Date date, String note) {
        this.fromAccount = fromAccount;
        this.monetaryAmount = monetaryAmount;
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

    public MonetaryAmount getAmount() {
        return monetaryAmount;
    }

    public void setAmount(MonetaryAmount monetaryAmount) {
        this.monetaryAmount = monetaryAmount;
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
