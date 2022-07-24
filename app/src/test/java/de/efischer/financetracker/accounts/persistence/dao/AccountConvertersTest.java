package de.efischer.financetracker.accounts.persistence.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

import de.efischer.financetracker.accounts.model.valueobjects.AccountType;
import de.efischer.financetracker.accounts.model.valueobjects.CreditCardType;

public class AccountConvertersTest {

    @Test
    public void testFromDate() {
        // arrange
        Instant now = Instant.now();
        Date date = Date.from(now);
        Long expectedTimestamp = now.toEpochMilli();

        // act
        Long result = AccountConverters.dateToTimestamp(date);

        // assert
        assertEquals(expectedTimestamp, result);
    }

    @Test
    public void testDateToTimestamp() {
        // arrange
        Instant now = Instant.now();
        Long timestamp = now.toEpochMilli();
        Date expectedDate = Date.from(now);

        // act
        Date result = AccountConverters.timestampToDate(timestamp);

        // assert
        assertEquals(expectedDate, result);
    }

    @Test
    public void testFromAccountTypeString() {
        // arrange
        String cashString = "CASH";
        String bankString = "BANK";
        String savingsString = "SAVINGS";
        String creditCardString = "CREDIT_CARD";

        // act
        AccountType resultFromCashString = AccountConverters.stringToAccountType(cashString);
        AccountType resultFromBankString = AccountConverters.stringToAccountType(bankString);
        AccountType resultFromSavingsString = AccountConverters.stringToAccountType(savingsString);
        AccountType resultFromCreditCardString = AccountConverters.stringToAccountType(creditCardString);

        // assert
        assertEquals(AccountType.CASH, resultFromCashString);
        assertEquals(AccountType.BANK, resultFromBankString);
        assertEquals(AccountType.SAVINGS, resultFromSavingsString);
        assertEquals(AccountType.CREDIT_CARD, resultFromCreditCardString);
    }

    @Test
    public void testAccountTypeToString() {
        // arrange
        String expectedCashString = "CASH";
        String expectedBankString = "BANK";
        String expectedSavingsString = "SAVINGS";
        String expectedCreditCardString = "CREDIT_CARD";

        // act
        String resultCashString = AccountConverters.accountTypeToString(AccountType.CASH);
        String resultBankString = AccountConverters.accountTypeToString(AccountType.BANK);
        String resultSavingsString = AccountConverters.accountTypeToString(AccountType.SAVINGS);
        String resultCreditCardString = AccountConverters.accountTypeToString(AccountType.CREDIT_CARD);

        // assert
        assertEquals(expectedCashString, resultCashString);
        assertEquals(expectedBankString, resultBankString);
        assertEquals(expectedSavingsString, resultSavingsString);
        assertEquals(expectedCreditCardString, resultCreditCardString);
    }

    @Test
    public void testCreditCardTypeToString() {
        // arrange
        String expectedVisaString = "VISA";
        String expectedMastercardString = "MASTERCARD";

        // act
        String resultVisaString = AccountConverters.creditCardTypeToString(CreditCardType.VISA);
        String resultMastercardString = AccountConverters.creditCardTypeToString(CreditCardType.MASTERCARD);

        // assert
        assertEquals(expectedVisaString, resultVisaString);
        assertEquals(expectedMastercardString, resultMastercardString);
    }

    @Test
    public void testFromCreditCardTypeString() {
        // arrange
        String visaString = "VISA";
        String mastercardString = "MASTERCARD";

        // act
        CreditCardType resultVisaType = AccountConverters.stringToCreditCardType(visaString);
        CreditCardType resultMastercardType = AccountConverters.stringToCreditCardType(mastercardString);

        // assert
        assertEquals(CreditCardType.VISA, resultVisaType);
        assertEquals(CreditCardType.MASTERCARD, resultMastercardType);
    }

    @Test
    public void testCurrencyToString() {
        // arrange
        String expectedResultStringEuro = "EUR";
        String expectedResultStringDollar = "USD";

        // act
        String resultEuroString = AccountConverters.currencyToString(
                Currency.getInstance(Locale.GERMANY));
        String resultDollarString = AccountConverters.currencyToString(
                Currency.getInstance(Locale.US));

        // assert
        assertEquals(expectedResultStringEuro, resultEuroString);
        assertEquals(expectedResultStringDollar, resultDollarString);
    }

    @Test
    public void testFromCurrencyString() {
        // arrange
        Currency expectedEuroCurrency = Currency.getInstance(Locale.GERMANY);
        Currency expectedDollarCurrency = Currency.getInstance(Locale.US);

        // act
        Currency resultEuroCurrency = AccountConverters.stringToCurrency("EUR");
        Currency resultDollarCurrency = AccountConverters.stringToCurrency("USD");

        // assert
        assertEquals(expectedEuroCurrency, resultEuroCurrency);
        assertEquals(expectedDollarCurrency, resultDollarCurrency);
    }

    @Test
    public void testBigDecimalToString() {
        // arrange
        String zeroAsString = "0";
        String positiveAmountAsString = "15.45";
        String negativeAmountAsString = "-12.48";

        // act
        String resultZeroAsString = AccountConverters.bigDecimalToString(BigDecimal.ZERO);
        String resultPositiveAmountAsString = AccountConverters.bigDecimalToString(BigDecimal.valueOf(15.45));
        String resultNegativeAmountAsString = AccountConverters.bigDecimalToString(BigDecimal.valueOf(-12.48));

        // assert
        assertEquals(zeroAsString, resultZeroAsString);
        assertEquals(positiveAmountAsString, resultPositiveAmountAsString);
        assertEquals(negativeAmountAsString, resultNegativeAmountAsString);
    }

    @Test
    public void testFromBigDecimalString() {
        // arrange
        String zeroAsString = "0";
        String positiveAmountString = "15.45";
        String negativeAmountString = "-12.48";
        String emptyString = "";

        // act
        BigDecimal resultBigDecimalZero = AccountConverters.stringToBigDecimal(zeroAsString);
        BigDecimal resultBigDecimalPositiveAmount = AccountConverters.stringToBigDecimal(positiveAmountString);
        BigDecimal resultBigDecimalNegativeAmount = AccountConverters.stringToBigDecimal(negativeAmountString);
        BigDecimal resultOfEmptyString = AccountConverters.stringToBigDecimal(emptyString);

        // assert
        assertEquals(BigDecimal.ZERO, resultBigDecimalZero);
        assertEquals(BigDecimal.ZERO, resultOfEmptyString);
        assertEquals(BigDecimal.valueOf(15.45), resultBigDecimalPositiveAmount);
        assertEquals(BigDecimal.valueOf(-12.48), resultBigDecimalNegativeAmount);
    }

}