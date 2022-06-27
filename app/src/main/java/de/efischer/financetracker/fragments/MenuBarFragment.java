package de.efischer.financetracker.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import de.efischer.financetracker.R;

public class MenuBarFragment extends Fragment {

    private TextView accountOverviewButton;
    private TextView transactionOverviewButton;
    private TextView budgetOverviewButton;
    private TextView settingsButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View menuBarView = inflater.inflate(R.layout.fragment_menu_bar, container, false);

        accountOverviewButton = menuBarView.findViewById(R.id.btn_overview);
        transactionOverviewButton = menuBarView.findViewById(R.id.btn_transactions);
        budgetOverviewButton = menuBarView.findViewById(R.id.btn_budgets);
        settingsButton = menuBarView.findViewById(R.id.btn_settings);

        setListeners();

        return menuBarView;
    }

    private void setListeners() {
        accountOverviewButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        transactionOverviewButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        budgetOverviewButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        settingsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}