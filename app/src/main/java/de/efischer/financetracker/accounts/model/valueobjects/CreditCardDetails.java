package de.efischer.financetracker.accounts.model.valueobjects;

import java.util.Objects;

public class CreditCardDetails {

    private String issuer;
    private String creditCardNumber;
    private CreditCardType creditCardType;

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCardDetails that = (CreditCardDetails) o;
        return Objects.equals(issuer, that.issuer) && Objects.equals(creditCardNumber, that.creditCardNumber) && creditCardType == that.creditCardType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(issuer, creditCardNumber, creditCardType);
    }
}
