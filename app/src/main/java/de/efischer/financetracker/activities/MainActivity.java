package de.efischer.financetracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import de.efischer.financetracker.R;
import de.efischer.financetracker.fragments.topbar.AccountOverviewFragment;
import de.efischer.financetracker.fragments.topbar.BudgetOverviewFragment;
import de.efischer.financetracker.fragments.topbar.MenuFragment;
import de.efischer.financetracker.fragments.topbar.StatisticsFragment;
import de.efischer.financetracker.fragments.topbar.TopBarFragment;
import de.efischer.financetracker.fragments.topbar.TransactionOverviewFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTopBarListener();

        // Set initial view
        setAccountOverviewFragment();
    }

    private void setTopBarListener() {
        getSupportFragmentManager().setFragmentResultListener(TopBarFragment.REQUEST_KEY, this, (requestKey, result) -> {

            switch(result.getString(TopBarFragment.RESULT_KEY)) {
                case TopBarFragment.ACCOUNT_TAB:
                    setAccountOverviewFragment();
                    break;
                case TopBarFragment.TRANSACTION_TAB:
                    setTransactionOverviewFragment();
                    break;
                case TopBarFragment.BUDGET_TAB:
                    setBudgetOverviewFragment();
                    break;
                case TopBarFragment.STATS_TAB:
                    setStatisticsFragment();
                    break;
                case TopBarFragment.MENU_TAB:
                    setMenuFragment();
            }
        });
    }

    private void setAccountOverviewFragment() {
        Fragment accountOverview = new AccountOverviewFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, accountOverview).commit();
    }

    private void setTransactionOverviewFragment() {
        Fragment transactionOverview = new TransactionOverviewFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, transactionOverview).commit();
    }

    private void setBudgetOverviewFragment() {
        Fragment budgetOverview = new BudgetOverviewFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, budgetOverview).commit();
    }

    private void setStatisticsFragment() {
        Fragment statisticsFragment = new StatisticsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, statisticsFragment).commit();
    }

    private void setMenuFragment() {
        Fragment menuFragment = new MenuFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, menuFragment).commit();
    }

    public void addAccount(View view) {
        Intent intent = new Intent(this, AddAccountActivity.class);
        startActivity(intent);
    }
}