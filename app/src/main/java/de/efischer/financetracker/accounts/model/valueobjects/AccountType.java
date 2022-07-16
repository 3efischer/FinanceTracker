package de.efischer.financetracker.accounts.model.valueobjects;

import java.io.Serializable;

import de.efischer.financetracker.R;

public enum AccountType implements Serializable {

    CASH(R.string.cash, R.drawable.ic_cash),
    BANK(R.string.bank_account, R.drawable.ic_bank),
    SAVINGS(R.string.savings, R.drawable.ic_savings),
    CREDIT_CARD(R.string.credit_card, R.drawable.ic_credit_card);

    public final int name;
    public final int iconId;

    AccountType(int nameId, int iconId) {
        this.name = nameId;
        this.iconId = iconId;
    }
}

