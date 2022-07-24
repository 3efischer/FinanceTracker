package de.efischer.financetracker.accounts.ui.list;

import static androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_DRAG;
import static androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_IDLE;
import static androidx.recyclerview.widget.ItemTouchHelper.DOWN;
import static androidx.recyclerview.widget.ItemTouchHelper.UP;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.efischer.financetracker.accounts.model.entities.Account;
import de.efischer.financetracker.accounts.persistence.AccountListViewModel;

public class AccountItemTouchCallback extends ItemTouchHelper.Callback {

    private AccountListViewModel accountListViewModel;
    private AccountListAdapter accountListAdapter;

    public AccountItemTouchCallback(AccountListViewModel accountListViewModel, AccountListAdapter accountListAdapter) {
        this.accountListViewModel = accountListViewModel;
        this.accountListAdapter = accountListAdapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeFlag(ACTION_STATE_DRAG, UP | DOWN);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        this.accountListAdapter.moveItem(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        switch (actionState) {
            case ACTION_STATE_DRAG:
                this.accountListAdapter.notifyItemDragStarted();
                break;
            case ACTION_STATE_IDLE:
                List<Account> dragEndedResultList = this.accountListAdapter.getDragEndedResultList();
                accountListViewModel.refreshListOrder(dragEndedResultList);
                break;

        }
    }


    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }
}
