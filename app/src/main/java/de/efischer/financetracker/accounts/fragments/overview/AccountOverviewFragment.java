package de.efischer.financetracker.accounts.fragments.overview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import de.efischer.financetracker.accounts.activities.AddAccountActivity;
import de.efischer.financetracker.accounts.model.entities.Account;
import de.efischer.financetracker.common.ApplicationDatabase;
import de.efischer.financetracker.databinding.FragmentAccountOverviewBinding;

/**
 * Fragment that represents a list of all accounts.
 *
 * @author Evelyn Fischer
 */

@AndroidEntryPoint
public class AccountOverviewFragment extends Fragment {

    @Inject
    ApplicationDatabase database;

    private FragmentAccountOverviewBinding binding;
    private List<Account> accounts;

    private ActivityResultLauncher<Intent> addAccountResultLauncher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accounts = Objects.requireNonNullElseGet(database.accountDao().getAll().getValue(), ArrayList::new);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.binding = FragmentAccountOverviewBinding.inflate(inflater, container, false);

        AccountListItemAdapter adapter = new AccountListItemAdapter(accounts);

        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(binding.recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        // Create touch helper for drag and drop and attach to recyclerview
        ItemTouchHelper.Callback callback = new AccountItemMovementTouchHelper(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(binding.recyclerView);

        // Handle changes on recyclerview
        if (accounts.isEmpty()) {
            binding.recyclerView.setVisibility(View.GONE);
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
                binding.recyclerView.setVisibility(adapter.getItemCount() > 0 ? View.VISIBLE : View.GONE);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Callback for AddAccount
        this.addAccountResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Log.println(Log.INFO, null, "New account created.");

                        assert result.getData() != null;
                        Account account = (Account) result.getData().getSerializableExtra("account");

                        database.accountDao().insert(account);

                        this.accounts.add(account);
                        binding.recyclerView.getAdapter().notifyItemInserted(accounts.size() - 1);


                    } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                        Log.println(Log.INFO, null, "Adding account aborted.");
                    }
                }
        );

        binding.addAccountFab.setOnClickListener(v -> addAccount());
    }

    public void addAccount() {
        Intent intent = new Intent(getActivity(), AddAccountActivity.class);
        this.addAccountResultLauncher.launch(intent);
    }
}

