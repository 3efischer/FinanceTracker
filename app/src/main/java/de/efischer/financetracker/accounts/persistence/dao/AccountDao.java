package de.efischer.financetracker.accounts.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

import de.efischer.financetracker.accounts.model.entities.Account;

@Dao
public interface AccountDao {

    @Insert
    void insert(Account account);

    @Update
    int update(Account account);

    @Update
    void update(List<Account> accounts);

    @Delete
    ListenableFuture<Integer> delete(Account account);

    @Query("SELECT * FROM account")
    LiveData<List<Account>> getAll();

    @Query("SELECT * FROM account ORDER BY sort_order ASC")
    LiveData<List<Account>> getAllSorted();

    @Query("SELECT * FROM account ORDER BY id ASC")
    List<Account> getAllSortedById();

    @Query("SELECT * FROM account WHERE id=:id")
    Account getAccount(int id);

    @Query("DELETE FROM account")
    void deleteAllEntries();

    @Query("SELECT COUNT(id) FROM account")
    int getAccountItemsCount();
}
