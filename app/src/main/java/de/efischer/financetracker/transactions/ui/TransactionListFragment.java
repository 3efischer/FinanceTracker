package de.efischer.financetracker.transactions.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import de.efischer.financetracker.R;

/**
 * Fragment that represents an overview over all transactions.
 * @author Evelyn Fischer
 */
public class TransactionListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaction_overview, container, false);
    }
}