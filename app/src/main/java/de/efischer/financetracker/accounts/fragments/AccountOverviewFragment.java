package de.efischer.financetracker.accounts.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.efischer.financetracker.R;
import de.efischer.financetracker.accounts.model.entities.Account;

/**
 * Fragment that represents a list of all accounts.
 * @author Evelyn Fischer
 */

public class AccountOverviewFragment extends Fragment {

    private RecyclerView recyclerView;
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
        recyclerView = view.findViewById(R.id.recyclerView);

        // Create and attach list item adapter to recycler view
        AccountListItemAdapter adapter = new AccountListItemAdapter(accounts);
        recyclerView.setAdapter(adapter);

        // Set divider between items
        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(recyclerView.getContext(), R.drawable.horizontal_divider));
        recyclerView.addItemDecoration(divider);

        // Create touch helper for drag and drop and attach to recyclerview
        ItemTouchHelper.Callback callback = new AccountItemMovementTouchHelper(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);

        // Handle changes on recyclerview
        if(accounts.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
        }
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                checkIfEmpty();
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                checkIfEmpty();
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                super.onItemRangeRemoved(positionStart, itemCount);
                checkIfEmpty();
            }

            public void checkIfEmpty() {
                recyclerView.setVisibility(adapter.getItemCount() > 0 ? View.VISIBLE : View.GONE);
            }
        });

        return view;
    }

    private void initDataset() {

        this.accounts = new ArrayList<>();
        //this.accounts.add(new Account("Bargeld", AccountType.CASH, Amount.of(new BigDecimal(25), Currency.getInstance("EUR"))));
//        this.accounts.add(new Account("Girokonto", AccountType.BANK, Amount.of(new BigDecimal(2000), Currency.getInstance("EUR"))));
//        this.accounts.add(new Account("Sparbuch", AccountType.SAVINGS, Amount.of(new BigDecimal(-125), Currency.getInstance("EUR"))));
//        this.accounts.add(new Account("Kreditkarte", AccountType.CREDIT_CARD, Amount.of(new BigDecimal(5000), Currency.getInstance("EUR"))));
    }
}

