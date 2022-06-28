package de.efischer.financetracker.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import de.efischer.financetracker.R;

public class TopBarFragment extends Fragment {

    private FragmentSwipeAdapter fragmentSwipeAdapter;
    private ViewPager2 viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top_bar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        fragmentSwipeAdapter = new FragmentSwipeAdapter(this);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(fragmentSwipeAdapter);

        TabLayout tabLayout = view.findViewById(R.id.contentTabs);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {

            switch(position) {
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