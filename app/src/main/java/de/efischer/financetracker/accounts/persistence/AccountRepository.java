package de.efischer.financetracker.accounts.persistence;

import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import de.efischer.financetracker.accounts.model.entities.Account;
import de.efischer.financetracker.accounts.persistence.dao.AccountDao;

public class AccountRepository {

    private final String TAG = AccountRepository.class.getSimpleName();

    private AccountDao accountDao;
    private LiveData<List<Account>> accountList;

    @Inject
    public AccountRepository(AccountDao accountDao) {
        this.accountDao = accountDao;
        this.accountList = accountDao.getAllSorted();
    }

    public LiveData<List<Account>> getAll() {
        return this.accountList;
    }

    public void insert(Account account) {
        Executors.newSingleThreadExecutor().execute(() -> {
            int accountItemCount = accountDao.getAccountItemsCount();
            account.setSortOrder(accountItemCount);
            accountDao.insert(account);
        });

        Log.println(Log.DEBUG, TAG, "New account inserted in database.");
    }

    public void refreshAccountList(List<Account> refreshedAccountList) {
        Executors.newSingleThreadExecutor().execute(() -> {
            refreshedAccountList.sort(Comparator.comparingInt(Account::getId));

            ArrayList<Account> accountsSortedById = new ArrayList<>(accountDao.getAllSortedById());

            for (int i = 0; i < accountsSortedById.size(); i++) {
                Account accountFromDb = accountsSortedById.get(i);
                int newSortOrder = refreshedAccountList.get(i).getSortOrder();

                if (accountFromDb.getSortOrder() != newSortOrder) {
                    accountFromDb.setSortOrder(newSortOrder);
                    accountDao.update(accountFromDb);
                }
            }
        });
    }
}
