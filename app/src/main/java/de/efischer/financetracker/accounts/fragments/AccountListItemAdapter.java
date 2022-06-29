package de.efischer.financetracker.accounts.fragments;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.efischer.financetracker.R;
import de.efischer.financetracker.accounts.model.entities.Account;

public class AccountListItemAdapter extends RecyclerView.Adapter<AccountListItemAdapter.ViewHolder>{

    private static final String TAG = "AccountListAdapter";
    private List<Account> accounts;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView firstLine;
        private final TextView accountName;
        private final TextView lastChangedDate;
        private final TextView amount;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            firstLine = (TextView) v.findViewById(R.id.first_row_text);
            accountName = (TextView) v.findViewById(R.id.account_title);
            lastChangedDate = (TextView) v.findViewById(R.id.last_account_changed_date);
            amount = (TextView) v.findViewById(R.id.total_amount);
        }

        public TextView getFirstLine() {
            return firstLine;
        }
        public TextView getAccountName() { return accountName; }
        public TextView getLastChangedDate() { return lastChangedDate; }
        public TextView getAmount() { return amount; }
    }

    public AccountListItemAdapter(List<Account> accounts) { this.accounts = accounts; }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.account_row_item, viewGroup, false);

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Replace textview texts with data
        Account account = accounts.get(position);
        viewHolder.getFirstLine().setText(account.getType().toString());
        viewHolder.getAmount().setText(account.getBalance().toString());
        viewHolder.getLastChangedDate().setText(account.getLastChanged().toString());
        viewHolder.getAccountName().setText(account.getName());
    }

    @Override
    public int getItemCount() {
        return this.accounts.size();
    }

    public void moveItem(int oldPosition, int newPosition) {
        Account accountToMove = accounts.get(oldPosition);
        accounts.remove(oldPosition);
        accounts.add(newPosition, accountToMove);

        notifyItemMoved(oldPosition, newPosition);
    }

}
