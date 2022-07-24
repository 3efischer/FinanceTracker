package de.efischer.financetracker.common.configuration;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import de.efischer.financetracker.accounts.persistence.AccountRepository;
import de.efischer.financetracker.accounts.persistence.dao.AccountDao;
import de.efischer.financetracker.accounts.persistence.dao.CreditCardDetailsDao;
import de.efischer.financetracker.common.ApplicationDatabase;

@Module
@InstallIn(SingletonComponent.class)
public class AccountPackageConfiguration {

    @Provides
    @Singleton
    public AccountRepository accountListRepository(ApplicationDatabase database) {
        return new AccountRepository(accountDao(database));
    }

    @Provides
    @Singleton
    public CreditCardDetailsDao creditCardDetailsDao(ApplicationDatabase database) {
        return database.creditCardDetailsDao();
    }

    @Provides
    @Singleton
    public AccountDao accountDao(ApplicationDatabase database) {
        return database.accountDao(database);
    }
}
