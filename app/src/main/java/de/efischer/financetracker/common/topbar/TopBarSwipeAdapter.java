package de.efischer.financetracker.common.topbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import de.efischer.financetracker.accounts.fragments.AccountOverviewFragment;
import de.efischer.financetracker.budgets.BudgetOverviewFragment;
import de.efischer.financetracker.menu.MenuFragment;
import de.efischer.financetracker.statistics.StatisticsFragment;
import de.efischer.financetracker.transactions.TransactionOverviewFragment;

public class TopBarSwipeAdapter extends FragmentStateAdapter {

    public TopBarSwipeAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment selectedFragment;

        switch (position) {
            case 0:
            default:
                selectedFragment = new AccountOverviewFragment();
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
