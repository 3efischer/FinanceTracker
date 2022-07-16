package de.efischer.financetracker.transactions.model.entities;

import de.efischer.financetracker.accounts.model.entities.Account;
import de.efischer.financetracker.accounts.model.valueobjects.Amount;

public class Transfer {

    private Account fromAccount;
    private Account toAccount;
    private Amount amount;
    private Category category;
    private String note;

    public Transfer(Account fromAccount, Account toAccount, Amount amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
