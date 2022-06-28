package de.efischer.financetracker.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

import de.efischer.financetracker.R;

public class TopBarFragment extends Fragment {

    private TabLayout tabLayout;

    public static final String REQUEST_KEY = "SELECTED_TAB";
    public static final String RESULT_KEY = "TAB_CLICKED";

    public static final String ACCOUNT_TAB = "ACCOUNT_TAB";
    public static final String TRANSACTION_TAB = "TRANSACTION_TAB";
    public static final String BUDGET_TAB = "BUDGET_TAB";
    public static final String STATS_TAB = "STATS_TAB";
    public static final String MENU_TAB = "MENU_TAB";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View menuBarView = inflater.inflate(R.layout.fragment_top_bar, container, false);

        tabLayout = menuBarView.findViewById(R.id.contentTabs);
        setupListeners();

        return menuBarView;
    }

    private void setupListeners() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                String selectedTab = null;

                switch(tab.getPosition()) {
                    case 0:
                        selectedTab = ACCOUNT_TAB;
                        break;
                    case 1:
                        selectedTab = TRANSACTION_TAB;
                        break;
                    case 2:
                        selectedTab = BUDGET_TAB;
                        break;
                    case 3:
                        selectedTab = STATS_TAB;
                        break;
                    case 4:
                        selectedTab = MENU_TAB;
                        break;
                }

                Bundle result = new Bundle();
                result.putString(RESULT_KEY, selectedTab);
                // The child fragment needs to still set the result on its parent fragment manager
                getParentFragmentManager().setFragmentResult(REQUEST_KEY, result);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

}