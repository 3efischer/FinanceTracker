package de.efischer.financetracker.accounts.fragments;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        private final ImageView icon;

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
        public TextView getAccountName() { return accountName; }
        public TextView getLastChangedDate() { return lastChangedDate; }
        public TextView getAmount() { return amount; }
        public ImageView getIcon() { return icon; }
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
        viewHolder.getIcon().setBackgroundResource(account.getType().iconId);
        viewHolder.getFirstLine().setText(account.getType().name);

        viewHolder.getAmount().setText(account.getBalance().toString());

        int green = viewHolder.getAmount().getContext().getResources().getColor(R.color.pos_green, null);
        int red = viewHolder.getAmount().getContext().getResources().getColor(R.color.neg_red, null);

        viewHolder.getAmount().setTextColor(account.getBalance().isPositive() ? green : red);


        viewHolder.getLastChangedDate().setText(account.getLastDayChanged());
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
