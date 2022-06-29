package de.efischer.financetracker.accounts.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import de.efischer.financetracker.R;
import de.efischer.financetracker.accounts.model.entities.Account;
import de.efischer.financetracker.accounts.model.valueobjects.AccountType;
import de.efischer.financetracker.accounts.model.valueobjects.Amount;

/**
 * Fragment that represents a list of all accounts.
 * @author Evelyn Fischer
 */

public class AccountOverviewFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private List<Account> accounts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account_overview, container, false);

        // Set layout for recycler view
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // Set custom adapter for recycler view
        AccountListItemAdapter adapter = new AccountListItemAdapter(accounts);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void initDataset() {

        this.accounts = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            accounts.add(new Account("Account " + (i+1), AccountType.CASH, Amount.createAmount(BigDecimal.valueOf(50 + i), Currency.getInstance("EUR"))));
        }
    }
}

