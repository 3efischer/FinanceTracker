package de.efischer.financetracker.common;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import de.efischer.financetracker.accounts.ui.list.AccountListOverviewFragment;
import de.efischer.financetracker.budgets.ui.BudgetListFragment;
import de.efischer.financetracker.settings.ui.SettingsFragment;
import de.efischer.financetracker.statistics.ui.StatisticsFragment;
import de.efischer.financetracker.transactions.ui.TransactionListFragment;

public class SwipeAdapter extends FragmentStateAdapter {

    public SwipeAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment selectedFragment;

        switch (position) {
            case 0:
            default:
                selectedFragment = new AccountListOverviewFragment();
                break;
            case 1:
                selectedFragment = new TransactionListFragment();
                break;
            case 2:
                selectedFragment = new BudgetListFragment();
                break;
            case 3:
                selectedFragment = new StatisticsFragment();
                break;
            case 4:
                selectedFragment = new SettingsFragment();
                break;
        }

        return selectedFragment;
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
