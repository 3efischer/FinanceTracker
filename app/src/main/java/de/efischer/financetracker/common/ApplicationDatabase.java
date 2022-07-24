package de.efischer.financetracker.common;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import de.efischer.financetracker.accounts.model.entities.Account;
import de.efischer.financetracker.accounts.model.entities.CreditCardDetails;
import de.efischer.financetracker.accounts.persistence.dao.AccountConverters;
import de.efischer.financetracker.accounts.persistence.dao.AccountDao;
import de.efischer.financetracker.accounts.persistence.dao.CreditCardDetailsDao;

@Database(entities = {Account.class, CreditCardDetails.class}, version = 3)
@TypeConverters({AccountConverters.class})
public abstract class ApplicationDatabase extends RoomDatabase {

    public abstract AccountDao accountDao(ApplicationDatabase database);

    public abstract CreditCardDetailsDao creditCardDetailsDao();
}
