package de.efischer.financetracker.common.topbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayoutMediator;

import de.efischer.financetracker.R;
import de.efischer.financetracker.databinding.FragmentTopBarBinding;

public class TopBarFragment extends Fragment {

    private FragmentTopBarBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentTopBarBinding.inflate(inflater, container, false);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        binding.contentContainer.setAdapter(new TopBarSwipeAdapter(this));

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
                    tab.setIcon(R.drawable.ic_menu);
                    tab.setText(R.string.menu);
                    break;
            }
        }).attach();
    }
}