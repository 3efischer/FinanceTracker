package de.efischer.financetracker.accounts.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.Map;

import de.efischer.financetracker.accounts.model.entities.Account;
import de.efischer.financetracker.accounts.model.entities.CreditCardDetails;
import de.efischer.financetracker.common.ApplicationDatabase;

@Dao
public abstract class AccountDao {

    private final CreditCardDetailsDao creditCardDetailsDao;

    public AccountDao(ApplicationDatabase database) {
        this.creditCardDetailsDao = database.creditCardDetailsDao();
    }

    @Insert
    public abstract long insert(Account account);

    @Update
    public abstract int update(Account account);

    @Update
    public abstract void update(List<Account> accounts);

    @Delete
    public abstract ListenableFuture<Integer> delete(Account account);

    @Query("DELETE FROM account WHERE id=:id")
    public abstract void delete(long id);

    @Query("SELECT * FROM account")
    public abstract LiveData<List<Account>> getAll();

    @Query("SELECT * FROM account ORDER BY list_position ASC")
    public abstract LiveData<List<Account>> getAllSorted();

    @Query("SELECT * FROM account ORDER BY id ASC")
    public abstract List<Account> getAllSortedById();

    @Query("SELECT * FROM account WHERE id=:id")
    public abstract Account getAccount(int id);

    @Query("DELETE FROM account")
    public abstract void deleteAllEntries();

    @Query("SELECT COUNT(id) FROM account")
    public abstract int getAccountItemsCount();

    @Query("UPDATE account SET list_position=:newListPosition WHERE id = :id")
    public abstract void updateListPosition(long id, int newListPosition);

    @Transaction
    public void updateListPositionForAccounts(Map<Account, Integer> accountsToUpdate) {
        accountsToUpdate.forEach((account, listPosition) -> updateListPosition(account.getId(), listPosition));
    }

    @Transaction
    public void insertAccount(Account account, CreditCardDetails creditCardDetails) {
        int listPosition = getAccountItemsCount();
        account.setListPosition(listPosition);
        long insertedAccountId = insert(account);

        creditCardDetails.setAccountId(insertedAccountId);
        creditCardDetailsDao.insert(creditCardDetails);
    }
}
