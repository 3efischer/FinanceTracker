package de.efischer.financetracker.accounts.persistence;

import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import de.efischer.financetracker.accounts.model.entities.Account;
import de.efischer.financetracker.accounts.model.entities.CreditCardDetails;
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
            account.setListPosition(accountItemCount);
            accountDao.insert(account);
        });

        Log.println(Log.DEBUG, TAG, "New account inserted in database.");
    }

    public void insert(Account account, CreditCardDetails creditCardDetails) {
        Executors.newSingleThreadExecutor().execute(() -> {
            accountDao.insertAccount(account, creditCardDetails);
            Log.println(Log.DEBUG, TAG, "New account and credit card details inserted in database.");
        });
    }

    public void refreshAccountList(List<Account> refreshedAccountList) {
        Executors.newSingleThreadExecutor().execute(() -> {

            refreshedAccountList.sort(Comparator.comparingLong(Account::getId));
            ArrayList<Account> accountsSortedById = new ArrayList<>(accountDao.getAllSortedById());

            Map<Account, Integer> accountsWithNewPosition = new HashMap<>();

            for (int i = 0; i < accountsSortedById.size(); i++) {
                Account accountFromDb = accountsSortedById.get(i);
                int newListPosition = refreshedAccountList.get(i).getListPosition();

                if (accountFromDb.getListPosition() != newListPosition) {
                    accountsWithNewPosition.put(accountFromDb, newListPosition);
                }
            }

            accountDao.updateListPositionForAccounts(accountsWithNewPosition);
        });
    }

    public void deleteAccount(long accountId) {
        Executors.newSingleThreadExecutor().execute(() -> accountDao.delete(accountId));
    }

    public void updateAccount(Account account) {
        Executors.newSingleThreadExecutor().execute(() -> accountDao.update(account));
    }
}
