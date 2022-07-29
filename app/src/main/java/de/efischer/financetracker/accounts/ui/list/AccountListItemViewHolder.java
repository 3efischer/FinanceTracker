package de.efischer.financetracker.accounts.ui.list;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.MenuRes;
import androidx.recyclerview.widget.RecyclerView;

import de.efischer.financetracker.R;

public class AccountListItemViewHolder extends RecyclerView.ViewHolder {

    private long accountId;
    private IAccountListItemObserver observer;

    private final TextView firstLine;
    private final TextView accountName;
    private final TextView lastChangedDate;
    private final TextView amount;
    private final ImageView icon;
    private final String TAG = AccountListItemViewHolder.class.getSimpleName();

    public AccountListItemViewHolder(View v) {
        super(v);

        firstLine = v.findViewById(R.id.first_row_text);
        accountName = v.findViewById(R.id.account_title);
        lastChangedDate = v.findViewById(R.id.last_account_changed_date);
        amount = v.findViewById(R.id.total_amount);
        icon = v.findViewById(R.id.account_icon);

        ImageButton menuButton = v.findViewById(R.id.menu_button);
        menuButton.setOnClickListener(btnView -> showMenu(btnView, R.menu.account_popup_menu));
    }

    private void showMenu(View v, @MenuRes int menuRes) {
        PopupMenu popup = new PopupMenu(v.getContext(), v);
        popup.getMenuInflater().inflate(menuRes, popup.getMenu());

        popup.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.option_edit) {
                Log.println(Log.ERROR, null, "edit pressed");

            } else if (item.getItemId() == R.id.option_delete) {
//                new MaterialAlertDialogBuilder(v.getContext(),
//                        com.google.android.material.R.style.Theme_AppCompat_DayNight_Dialog_Alert)
//                        .setMessage(v.getContext().getResources().getString(R.string.delete_alert_description))
//                        .setNegativeButton(v.getContext().getString(R.string.decline), (dialog, which) -> {
//                            dialog.cancel();
//                        })
//                        .setPositiveButton(v.getContext().getResources().getString(R.string.accept), (dialog, which) -> {
//
//                        }).show();
            }
            return true;
        });

        popup.show();
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public void addAccountListItemObserver(IAccountListItemObserver observer) {
        this.observer = observer;
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
}
