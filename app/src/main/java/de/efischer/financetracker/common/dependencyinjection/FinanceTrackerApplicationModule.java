package de.efischer.financetracker.common.dependencyinjection;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import de.efischer.financetracker.accounts.persistence.AccountRepository;
import de.efischer.financetracker.accounts.persistence.dao.AccountDao;
import de.efischer.financetracker.common.ApplicationDatabase;

@Module
@InstallIn(SingletonComponent.class)

public class FinanceTrackerApplicationModule {

    @Provides
    @Singleton
    public ApplicationDatabase database(@ApplicationContext Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), ApplicationDatabase.class, "database").build();
    }

    @Provides
    @Singleton
    public AccountRepository accountListRepository(ApplicationDatabase database) {
        return new AccountRepository(accountDao(database));
    }

    @Provides
    @Singleton
    public AccountDao accountDao(ApplicationDatabase database) {
        return database.accountDao();
    }
}
