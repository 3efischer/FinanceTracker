package de.efischer.financetracker.accounts.model.valueobjects;

public class CreditCardDetails {

    private String creditCardNumber;
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

    @Override
    public String toString() {
        return "CreditCardDetails{" +
                "creditCardNumber='" + creditCardNumber + '\'' +
                ", creditCardType=" + creditCardType +
                ", creditLimit=" + creditLimit +
                '}';
    }
}
