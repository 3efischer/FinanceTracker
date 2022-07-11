package de.efischer.financetracker.accounts.model.valueobjects;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public class Amount {

    private final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_EVEN;

    private final BigDecimal amount;
    private final Currency currency;

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

    public boolean isPositive() {
        return amount.signum() >= 0;
    }

    @NonNull
    @Override
    public String toString() {
        return amount + " " + currency.getSymbol();
    }
}
