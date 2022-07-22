package de.efischer.financetracker.accounts.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import de.efischer.financetracker.accounts.model.entities.Account;

@HiltViewModel
public class AccountListViewModel extends ViewModel {

    private final String TAG = AccountListViewModel.class.getSimpleName();

    private AccountListRepository repository;

    @Inject
    public AccountListViewModel(AccountListRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Account>> getAccountList() {
        return repository.getAll();
    }

    public void addAccount(Account account) {
        repository.insert(account);
    }

    public void moveItem(Account account, int newPosition) {

    }

}
