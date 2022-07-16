package de.efischer.financetracker.common;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import de.efischer.financetracker.accounts.model.entities.Account;
import de.efischer.financetracker.accounts.persistence.AccountConverters;
import de.efischer.financetracker.accounts.persistence.AccountDao;

@Database(entities = {Account.class}, version = 1)
@TypeConverters({AccountConverters.class})
public abstract class ApplicationDatabase extends RoomDatabase {

    public abstract AccountDao accountDao();
}
