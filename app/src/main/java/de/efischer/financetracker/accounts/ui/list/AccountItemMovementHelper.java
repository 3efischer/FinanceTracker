package de.efischer.financetracker.accounts.ui.list;

import static androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_DRAG;
import static androidx.recyclerview.widget.ItemTouchHelper.UP;
import static androidx.recyclerview.widget.ItemTouchHelper.DOWN;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class AccountItemMovementHelper extends ItemTouchHelper.Callback {

    AccountListItemAdapter adapter;

    public AccountItemMovementHelper(AccountListItemAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeFlag(ACTION_STATE_DRAG, UP | DOWN);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

        adapter.moveItem(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }
}
