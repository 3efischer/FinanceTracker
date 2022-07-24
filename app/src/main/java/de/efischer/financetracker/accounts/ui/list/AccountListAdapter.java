package de.efischer.financetracker.accounts.ui.list;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.efischer.financetracker.R;
import de.efischer.financetracker.accounts.model.entities.Account;

public class AccountListAdapter extends ListAdapter<Account, AccountListAdapter.ViewHolder> {

    private ArrayList<Account> tempAccountList;

    protected AccountListAdapter() {
        super(accountItemCallback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.account_list_item, parent, false);

        return new AccountListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Account account = getCurrentList().get(position);
        holder.setAccountId(account.getId());

        holder.getIcon().setBackgroundResource(account.getType().iconId);
        holder.getFirstLine().setText(account.getType().name);

        holder.getAmount().setText(account.getBalance().toString());

        int green = holder.getAmount().getContext().getResources().getColor(R.color.pos_green, null);
        int red = holder.getAmount().getContext().getResources().getColor(R.color.neg_red, null);

        holder.getAmount().setTextColor(account.getBalance().isPositive() ? green : red);

        holder.getLastChangedDate().setText(account.getLastDayChanged());
        holder.getAccountName().setText(account.getName());
    }

    public static final DiffUtil.ItemCallback<Account> accountItemCallback = new DiffUtil.ItemCallback<>() {
        @Override
        public boolean areItemsTheSame(@NonNull Account oldItem, @NonNull Account newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Account oldItem, @NonNull Account newItem) {
            return oldItem.equals(newItem);
        }
    };

    public void notifyItemDragStarted() {
        tempAccountList = new ArrayList<>(getCurrentList());
    }

    public void moveItem(int oldPosition, int newPosition) {
        Account accountToMove = tempAccountList.get(oldPosition);
        tempAccountList.remove(oldPosition);
        tempAccountList.add(newPosition, accountToMove);

        notifyItemMoved(oldPosition, newPosition);
    }

    public List<Account> getDragEndedResultList() {
        tempAccountList.forEach(account -> account.setListPosition(tempAccountList.indexOf(account)));
        return tempAccountList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private int accountId;
        private final TextView firstLine;
        private final TextView accountName;
        private final TextView lastChangedDate;
        private final TextView amount;
        private final ImageView icon;
        private final String TAG = ViewHolder.class.getSimpleName();

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(v1 -> Log.d(TAG, "Element " + getAdapterPosition() + " clicked."));

            firstLine = v.findViewById(R.id.first_row_text);
            accountName = v.findViewById(R.id.account_title);
            lastChangedDate = v.findViewById(R.id.last_account_changed_date);
            amount = v.findViewById(R.id.total_amount);
            icon = v.findViewById(R.id.account_icon);
        }

        public TextView getFirstLine() {
            return firstLine;
        }

        public TextView getAccountName() {
            return accountName;
        }

        public TextView getLastChangedDate() {
            return lastChangedDate;
        }

        public TextView getAmount() {
            return amount;
        }

        public ImageView getIcon() {
            return icon;
        }

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int id) {
            accountId = id;
        }
    }
}
