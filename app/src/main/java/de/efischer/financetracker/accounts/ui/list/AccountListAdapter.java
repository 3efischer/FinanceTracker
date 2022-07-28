package de.efischer.financetracker.accounts.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import de.efischer.financetracker.R;
import de.efischer.financetracker.accounts.model.entities.Account;

public class AccountListAdapter extends ListAdapter<Account, AccountListItemViewHolder> implements IAccountListItemObserver {

    private ArrayList<Account> tempAccountList;
    private IAccountListItemObserver observer;

    protected AccountListAdapter() {
        super(accountItemCallback);
    }

    @NonNull
    @Override
    public AccountListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.account_list_item, parent, false);

        return new AccountListItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountListItemViewHolder holder, int position) {
        Account account = getCurrentList().get(position);
        holder.setAccountId(account.getId());

        holder.getIcon().setBackgroundResource(account.getType().iconId);
        holder.getFirstLine().setText(account.getType().name);

        String formattedAmount = NumberFormat.getCurrencyInstance()
                .format(account.getBalance().getAmount());
        holder.getAmount().setText(formattedAmount);

        int green = holder.getAmount().getContext().getResources().getColor(R.color.pos_green, null);
        int red = holder.getAmount().getContext().getResources().getColor(R.color.neg_red, null);

        holder.getAmount().setTextColor(account.getBalance().isPositive() ? green : red);

        holder.getLastChangedDate().setText(account.getLastDayChanged());
        holder.getAccountName().setText(account.getName());

        holder.addAccountListItemObserver(this);
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

    public void setObserver(IAccountListItemObserver observer) {
        this.observer = observer;
    }

    @Override
    public void notifyItemDeleteRequest(long accountId) {
        observer.notifyItemDeleteRequest(accountId);
    }

    @Override
    public void notifyItemEditRequest(long accountId) {
        observer.notifyItemEditRequest(accountId);
    }
}
