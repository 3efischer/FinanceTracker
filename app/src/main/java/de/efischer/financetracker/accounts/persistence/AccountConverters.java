package de.efischer.financetracker.accounts.persistence;

import androidx.room.TypeConverter;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

import de.efischer.financetracker.accounts.model.valueobjects.AccountType;
import de.efischer.financetracker.accounts.model.valueobjects.CreditCardType;

public class AccountConverters {

    @TypeConverter
    public static Date fromDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static AccountType fromAccountTypeString(String accountType) {
        return AccountType.valueOf(accountType);
    }

    @TypeConverter
    public static String accountTypeToString(AccountType accountType) {
        return accountType.name();
    }

    @TypeConverter
    public static CreditCardType fromCreditCardTypeString(String creditCardType) {
        return CreditCardType.valueOf(creditCardType);
    }

    @TypeConverter
    public static String creditCardTypeToString(CreditCardType creditCardType) {
        return creditCardType.name();
    }

    @TypeConverter
    public static String currencyToString(Currency currency) {
        return currency.getCurrencyCode();
    }

    @TypeConverter
    public static Currency fromCurrencyString(String currency) {
        return Currency.getInstance(currency);
    }

    @TypeConverter
    public static String bigDecimalToString(BigDecimal bigDecimal) {
        return bigDecimal.toPlainString();
    }

    @TypeConverter
    public static BigDecimal fromBigDecimalString(String bigDecimal) {
        boolean isEmptyString = bigDecimal.trim().isEmpty();
        BigDecimal result;

        if (isEmptyString) {
            result = BigDecimal.ZERO;
        } else {
            result = new BigDecimal(bigDecimal);
        }

        return result;
    }

}
