package de.efischer.financetracker.accounts.ui.list;

public interface IAccountListItemObserver {

    void notifyItemDeleteRequest(long accountId);

    void notifyItemEditRequest(long accountId);
}
