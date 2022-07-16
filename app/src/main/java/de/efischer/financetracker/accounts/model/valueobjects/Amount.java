package de.efischer.financetracker.accounts.model.valueobjects;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Ignore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public class Amount implements Serializable {

    @Ignore
    private final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_EVEN;

    @ColumnInfo(name = "amount")
    private BigDecimal amount;

    @ColumnInfo(name = "currency")
    private Currency currency;
    
    public Amount() {
    }

    private Amount(BigDecimal amount, Currency currency) {
        this.amount = amount.setScale(currency.getDefaultFractionDigits(), DEFAULT_ROUNDING);
        this.currency = currency;
    }

    public static Amount of(BigDecimal amount, Currency currency) {
        return new Amount(amount, currency);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public boolean isPositive() {
        return amount.signum() >= 0;
    }

    @NonNull
    @Override
    public String toString() {
        return amount + " " + currency.getSymbol();
    }
}
