package de.efischer.financetracker.accounts.persistence.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import de.efischer.financetracker.accounts.model.entities.CreditCardDetails;

@Dao
public abstract class CreditCardDetailsDao {

    @Insert
    public abstract void insert(CreditCardDetails creditCardDetails);

    @Update
    public abstract int update(CreditCardDetails creditCardDetails);

    @Delete
    public abstract int delete(CreditCardDetails creditCardDetails);

    @Query("SELECT * FROM credit_card_details WHERE account_id =:accountId")
    public abstract CreditCardDetails getByAccountId(int accountId);
}
