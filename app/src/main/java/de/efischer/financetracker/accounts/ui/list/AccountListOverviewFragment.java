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

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;
import de.efischer.financetracker.accounts.model.entities.Account;
import de.efischer.financetracker.accounts.model.entities.CreditCardDetails;
import de.efischer.financetracker.accounts.persistence.AccountListViewModel;
import de.efischer.financetracker.accounts.ui.addaccount.AddAccountActivity;
import de.efischer.financetracker.databinding.FragmentAccountOverviewBinding;

/**
 * Fragment that represents a list of all accounts.
 *
 * @author Evelyn Fischer
 */

@AndroidEntryPoint
public class AccountListOverviewFragment extends Fragment implements IAccountListItemObserver {

    private FragmentAccountOverviewBinding binding;
    private AccountListViewModel accountListViewModel;

    private ActivityResultLauncher<Intent> addAccountResultLauncher;

    private final String TAG = AccountListOverviewFragment.class.getSimpleName();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountListViewModel = new ViewModelProvider(requireActivity()).get(AccountListViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.binding = FragmentAccountOverviewBinding.inflate(inflater, container, false);

        // This prevents flickering when moving an item in the list
        Objects.requireNonNull(binding.recyclerView.getItemAnimator()).setChangeDuration(0);

        AccountListAdapter accountListAdapter = new AccountListAdapter();
        binding.recyclerView.setAdapter(accountListAdapter);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(binding.recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        ItemTouchHelper.Callback itemDragCallback = new AccountItemDragCallback(accountListViewModel, accountListAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(itemDragCallback);
        touchHelper.attachToRecyclerView(binding.recyclerView);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        accountListViewModel.getAccountList().observe(requireActivity(), accounts -> {
            if (accounts.isEmpty()) {
                binding.recyclerView.setVisibility(View.GONE);
            } else {
                binding.recyclerView.setVisibility(View.VISIBLE);
            }

            AccountListAdapter adapter = (AccountListAdapter) binding.recyclerView.getAdapter();
            assert adapter != null;
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
                        CreditCardDetails creditCardDetails = (CreditCardDetails) result.getData().getSerializableExtra("creditCardDetails");

                        if (creditCardDetails == null) {
                            accountListViewModel.addAccount(account);
                        } else {
                            accountListViewModel.addAccount(account, creditCardDetails);
                        }

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

    @Override
    public void notifyItemDeleteRequest(long accountId) {

    }

    @Override
    public void notifyItemEditRequest(long accountId) {
        this.accountListViewModel.deleteAccount(accountId);
    }
}

