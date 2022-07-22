package de.efischer.financetracker.accounts.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import de.efischer.financetracker.accounts.model.entities.Account;
import de.efischer.financetracker.accounts.persistence.AccountDao;

public class AccountListRepository {

    private final String TAG = AccountListRepository.class.getSimpleName();

    private AccountDao accountDao;
    private LiveData<List<Account>> accountList;

    @Inject
    public AccountListRepository(AccountDao accountDao) {
        this.accountDao = accountDao;
        this.accountList = accountDao.getAll();
    }

    public LiveData<List<Account>> getAll() {
        return this.accountList;
    }

    public void insert(Account account) {
        Executors.newSingleThreadExecutor().execute(() -> accountDao.insert(account));
        Log.println(Log.DEBUG, TAG, "New account inserted in database.");
        // AsyncTask.execute(() -> accountDao.getAll().getValue());
    }
}
