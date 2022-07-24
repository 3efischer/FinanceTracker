package de.efischer.financetracker.accounts.ui.list;

import static androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_DRAG;
import static androidx.recyclerview.widget.ItemTouchHelper.DOWN;
import static androidx.recyclerview.widget.ItemTouchHelper.UP;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import de.efischer.financetracker.accounts.persistence.AccountListViewModel;

public class AccountItemTouchCallback extends ItemTouchHelper.Callback {

    private AccountListViewModel accountListViewModel;

    public AccountItemTouchCallback(AccountListViewModel accountListViewModel) {
        this.accountListViewModel = accountListViewModel;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeFlag(ACTION_STATE_DRAG, UP | DOWN);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        AccountListAdapter.ViewHolder accountItemViewHolder = (AccountListAdapter.ViewHolder) viewHolder;
        this.accountListViewModel.moveItem(accountItemViewHolder.getAccountId(), target.getAdapterPosition());

        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }
}
