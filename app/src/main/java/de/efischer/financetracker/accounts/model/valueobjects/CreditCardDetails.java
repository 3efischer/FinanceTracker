package de.efischer.financetracker.accounts.model.valueobjects;

import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import de.efischer.financetracker.accounts.model.entities.Account;

@Entity(foreignKeys = @ForeignKey(entity = Account.class, parentColumns = "id", childColumns = "account_id", onDelete = CASCADE))
public class CreditCardDetails {

    @ColumnInfo(name = "account_id")
    private int accountId;

    @ColumnInfo(name = "credit_card_number")
    private String creditCardNumber;

    @ColumnInfo(name = "credit_card_type")
    private CreditCardType creditCardType;

    private Amount creditLimit;

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public CreditCardType getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(CreditCardType creditCardType) {
        this.creditCardType = creditCardType;
    }

    public Amount getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Amount creditLimit) {
        this.creditLimit = creditLimit;
    }

    @NonNull
    @Override
    public String toString() {
        return "CreditCardDetails{" +
                "creditCardNumber='" + creditCardNumber + '\'' +
                ", creditCardType=" + creditCardType +
                ", creditLimit=" + creditLimit +
                '}';
    }
}
