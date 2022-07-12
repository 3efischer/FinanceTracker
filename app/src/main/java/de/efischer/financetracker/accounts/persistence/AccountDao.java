package de.efischer.financetracker.accounts.persistence;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import de.efischer.financetracker.accounts.model.entities.Account;

@Dao
public interface AccountDao {

    @Insert
    long insert(Account account);

    @Update
    int update(Account account);

    @Delete
    int delete(Account account);

    @Query("SELECT * FROM account")
    List<Account> getAll();

    @Query("SELECT * FROM account ORDER BY sort_order ASC")
    List<Account> getAllSorted();
}
