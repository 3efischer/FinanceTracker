package de.efischer.financetracker.accounts.model.valueobjects;

import de.efischer.financetracker.R;

public enum CreditCardType {

    VISA(R.string.visa, R.drawable.ic_visa),
    MASTERCARD(R.string.mastercard, R.drawable.ic_mastercard);

    public final int nameId;
    public final int iconId;

    CreditCardType(int nameId, int iconId) {
        this.nameId = nameId;
        this.iconId = iconId;
    }

}
