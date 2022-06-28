package de.efischer.financetracker.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import de.efischer.financetracker.R;

/**
 * Fragment that represents the overview over all budgets.
 * @author Evelyn Fischer
 */

public class BudgetOverviewFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_budget_overview, container, false);
    }
}