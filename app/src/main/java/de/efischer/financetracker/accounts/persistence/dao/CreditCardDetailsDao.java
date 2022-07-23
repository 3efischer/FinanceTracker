package de.efischer.financetracker.accounts.persistence.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import de.efischer.financetracker.accounts.model.entities.CreditCardDetails;

@Dao
public interface CreditCardDetailsDao {

    @Insert
    void insert(CreditCardDetails creditCardDetails);

    @Update
    int update(CreditCardDetails creditCardDetails);

    @Delete
    int delete(CreditCardDetails creditCardDetails);
}
