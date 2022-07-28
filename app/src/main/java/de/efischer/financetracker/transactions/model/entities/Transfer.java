package de.efischer.financetracker.transactions.model.entities;

import de.efischer.financetracker.accounts.model.entities.Account;
import de.efischer.financetracker.accounts.model.valueobjects.MonetaryAmount;

public class Transfer {

    private Account fromAccount;
    private Account toAccount;
    private MonetaryAmount monetaryAmount;
    private Category category;
    private String note;

    public Transfer(Account fromAccount, Account toAccount, MonetaryAmount monetaryAmount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.monetaryAmount = monetaryAmount;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
