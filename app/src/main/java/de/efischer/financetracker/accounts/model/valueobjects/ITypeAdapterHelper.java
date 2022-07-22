package de.efischer.financetracker.accounts.model.valueobjects;

import java.io.Serializable;

/**
 * This interface is implemented by Type classes so that the DropdownAdapter class that displays them
 * can process them by themself rather than the class which creates the adapter.
 */
public interface ITypeAdapterHelper extends Serializable {

    int getEnumName();

    int getEnumIcon();
}
