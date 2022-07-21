package de.efischer.financetracker.common;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import de.efischer.financetracker.accounts.ui.list.AccountListFragment;
import de.efischer.financetracker.budgets.BudgetOverviewFragment;
import de.efischer.financetracker.menu.MenuFragment;
import de.efischer.financetracker.statistics.StatisticsFragment;
import de.efischer.financetracker.transactions.TransactionOverviewFragment;

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
                selectedFragment = new AccountListFragment();
                break;
            case 1:
                selectedFragment = new TransactionOverviewFragment();
                break;
            case 2:
                selectedFragment = new BudgetOverviewFragment();
                break;
            case 3:
                selectedFragment = new StatisticsFragment();
                break;
            case 4:
                selectedFragment = new MenuFragment();
                break;
        }

        return selectedFragment;
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
