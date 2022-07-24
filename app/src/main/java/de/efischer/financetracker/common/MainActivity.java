package de.efischer.financetracker.common;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayoutMediator;

import dagger.hilt.android.AndroidEntryPoint;
import de.efischer.financetracker.R;
import de.efischer.financetracker.databinding.ActivityMainBinding;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(this.binding.getRoot());
        createTabs();
    }

    private void createTabs() {
        binding.contentContainer.setAdapter(new SwipeAdapter(getSupportFragmentManager(), getLifecycle()));

        new TabLayoutMediator(binding.contentTabs, binding.contentContainer, (tab, position) -> {

            switch (position) {
                case 0:
                    tab.setIcon(R.drawable.ic_wallet);
                    tab.setText(R.string.accounts);
                    break;
                case 1:
                    tab.setIcon(R.drawable.ic_transaction);
                    tab.setText(R.string.transactions);
                    break;
                case 2:
                    tab.setIcon(R.drawable.ic_budget);
                    tab.setText(R.string.budgets);
                    break;
                case 3:
                    tab.setIcon(R.drawable.ic_statistics);
                    tab.setText(R.string.stats);
                    break;
                case 4:
                    tab.setIcon(R.drawable.ic_settings);
                    tab.setText(R.string.settings);
                    break;
            }
        }).attach();
    }
}