package de.efischer.financetracker.accounts.model.entities;

import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import de.efischer.financetracker.accounts.model.valueobjects.CreditCardType;
import de.efischer.financetracker.accounts.model.valueobjects.MonetaryAmount;

@Entity(tableName = "credit_card_details", foreignKeys = @ForeignKey(entity = Account.class,
        parentColumns = "id", childColumns = "account_id", onDelete = CASCADE), indices = {
        @Index(value = {"account_id"}, unique = true)
})
public class CreditCardDetails implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "account_id")
    private long accountId;

    @ColumnInfo(name = "credit_card_number")
    private String creditCardNumber;

    @ColumnInfo(name = "credit_card_type")
    private CreditCardType creditCardType;

    @Embedded
    private MonetaryAmount creditLimit;

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CreditCardType getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(CreditCardType creditCardType) {
        this.creditCardType = creditCardType;
    }

    public MonetaryAmount getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(MonetaryAmount creditLimit) {
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

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }
}
