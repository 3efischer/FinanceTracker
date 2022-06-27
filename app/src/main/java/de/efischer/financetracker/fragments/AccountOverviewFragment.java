package de.efischer.financetracker.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.efischer.financetracker.R;

/**
 * Fragment that represents a list of all accounts.
 * @author Evelyn Fischer
 * @date 2022-06-27
 */

public class AccountOverviewFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_overview, container, false);
    }
}