package de.efischer.financetracker.accounts.model.valueobjects;

import de.efischer.financetracker.R;

public enum CreditCardType implements ITypeAdapterHelper {

    VISA(R.string.visa, R.drawable.ic_visa2),
    MASTERCARD(R.string.mastercard, R.drawable.ic_mastercard);

    public final int nameId;
    public final int iconId;

    CreditCardType(int nameId, int iconId) {
        this.nameId = nameId;
        this.iconId = iconId;
    }

    @Override
    public int getEnumName() {
        return nameId;
    }

    @Override
    public int getEnumIcon() {
        return iconId;
    }
}
