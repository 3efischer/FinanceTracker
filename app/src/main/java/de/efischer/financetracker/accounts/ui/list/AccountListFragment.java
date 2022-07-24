package de.efischer.financetracker.accounts.ui.list;

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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;

import dagger.hilt.android.AndroidEntryPoint;
import de.efischer.financetracker.accounts.model.entities.Account;
import de.efischer.financetracker.accounts.persistence.AccountListViewModel;
import de.efischer.financetracker.accounts.ui.addaccount.AddAccountActivity;
import de.efischer.financetracker.databinding.FragmentAccountOverviewBinding;

/**
 * Fragment that represents a list of all accounts.
 *
 * @author Evelyn Fischer
 */

@AndroidEntryPoint
public class AccountListFragment extends Fragment {

    private FragmentAccountOverviewBinding binding;
    private AccountListViewModel accountListViewModel;

    private ActivityResultLauncher<Intent> addAccountResultLauncher;

    private final String TAG = AccountListFragment.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountListViewModel = new ViewModelProvider(requireActivity()).get(AccountListViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.binding = FragmentAccountOverviewBinding.inflate(inflater, container, false);

        AccountListAdapter accountListAdapter = new AccountListAdapter();
        binding.recyclerView.getItemAnimator().setChangeDuration(0);
        binding.recyclerView.setAdapter(accountListAdapter);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(binding.recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        ItemTouchHelper.Callback itemTouchCallback = new AccountItemTouchCallback(accountListViewModel, accountListAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(itemTouchCallback);
        touchHelper.attachToRecyclerView(binding.recyclerView);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        accountListViewModel.getAccountList().observe(getActivity(), accounts -> {
            if (accounts.isEmpty()) {
                binding.recyclerView.setVisibility(View.GONE);
            } else {
                binding.recyclerView.setVisibility(View.VISIBLE);
            }

            AccountListAdapter adapter = (AccountListAdapter) binding.recyclerView.getAdapter();
            adapter.submitList(accounts);
        });

        setupAddButton();
    }

    private void setupAddButton() {
        this.addAccountResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Log.println(Log.INFO, TAG, "Closed AddAccount with save button.");

                        assert result.getData() != null;
                        Account account = (Account) result.getData().getSerializableExtra("account");
                        accountListViewModel.addAccount(account);

                    } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                        Log.println(Log.INFO, TAG, "Adding account aborted.");
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

