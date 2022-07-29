package de.efischer.financetracker.accounts.ui.addaccount;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import de.efischer.financetracker.R;
import de.efischer.financetracker.accounts.model.entities.Account;
import de.efischer.financetracker.accounts.model.entities.CreditCardDetails;
import de.efischer.financetracker.accounts.model.valueobjects.AccountType;
import de.efischer.financetracker.accounts.model.valueobjects.MonetaryAmount;
import de.efischer.financetracker.common.inputs.MonetaryAmountInputFragment;
import de.efischer.financetracker.common.inputs.TextInputFragment;
import de.efischer.financetracker.databinding.ActivityAddAccountBinding;

public class AddAccountActivity extends AppCompatActivity {

    private ActivityAddAccountBinding binding;
    private DropdownAdapter dropdownAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            setupAccountTypeChooser();
            setupAmountField();
            setupNameField();
            setupBankNameField();
            setupCreditCardLimitField();
            setupButtonListeners();
            binding.accountTypeDropdown.setOnItemSelectedListener(createDropdownListener());
        }
    }

    private void setupButtonListeners() {
        binding.saveButton.setOnClickListener(onClick -> onSaveButtonClicked());
        binding.abortButton.setOnClickListener(onClick -> onAbortButtonClicked());
    }

    private void setupNameField() {
        Bundle args = new Bundle();
        args.putInt(TextInputFragment.TEXTFIELD_HINT_KEY, R.string.account_name_description);
        args.putInt(TextInputFragment.INPUT_IS_MANDATORY_DESCRIPTION, R.string.mandatory_field);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.account_name_input_fragment, TextInputFragment.class, args)
                .commit();
    }

    private void setupAccountTypeChooser() {
        binding.accountTypeDropdown.setAdapter(new DropdownAdapter(this, R.layout.account_type_dropdown_item, AccountType.values()));
    }

    @NonNull
    private AdapterView.OnItemSelectedListener createDropdownListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Fragment creditCardDetailsFragment = binding.creditCardDetailsFragment.getFragment();
                Fragment creditCardLimitFragment = binding.creditCardLimitFragment.getFragment();
                Fragment bankNameFragment = binding.bankNameInputFragment.getFragment();

                AccountType selectedAccountType = AccountType.values()[binding.accountTypeDropdown.getSelectedItemPosition()];

                switch (selectedAccountType) {
                    case CREDIT_CARD:
                        getSupportFragmentManager().beginTransaction()
                                .show(bankNameFragment)
                                .show(creditCardDetailsFragment)
                                .show(creditCardLimitFragment)
                                .commit();
                        break;
                    case BANK:
                    case SAVINGS:
                        getSupportFragmentManager().beginTransaction()
                                .show(bankNameFragment)
                                .hide(creditCardDetailsFragment)
                                .hide(creditCardLimitFragment)
                                .commit();
                        break;
                    case CASH:
                    default:
                        getSupportFragmentManager().beginTransaction()
                                .hide(bankNameFragment)
                                .hide(creditCardDetailsFragment)
                                .hide(creditCardLimitFragment)
                                .commit();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

    private void setupAmountField() {
        Bundle args = new Bundle();
        args.putBoolean(MonetaryAmountInputFragment.IS_STARTING_POSITIVE, true);
        args.putInt(MonetaryAmountInputFragment.AMOUNT_TYPE_TITLE, R.string.initial_account_balance);
        args.putBoolean(MonetaryAmountInputFragment.SHOW_CURRENCY_LIST, true);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.amount_input_fragment, MonetaryAmountInputFragment.class, args)
                .commit();
    }

    private void setupCreditCardLimitField() {
        Bundle args = new Bundle();
        args.putInt(MonetaryAmountInputFragment.AMOUNT_TYPE_TITLE, R.string.credit_card_limit);
        args.putBoolean(MonetaryAmountInputFragment.IS_STARTING_POSITIVE, false);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.credit_card_limit_fragment, MonetaryAmountInputFragment.class, args)
                .commit();
    }

    private void setupBankNameField() {
        Bundle args = new Bundle();
        args.putInt(TextInputFragment.TEXTFIELD_HINT_KEY, R.string.bank_name);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.bank_name_input_fragment, TextInputFragment.class, args)
                .commit();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("selectedAccountIndex", binding.accountTypeDropdown.getSelectedItemPosition());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        setupAccountTypeChooser();
        binding.accountTypeDropdown.setSelection(savedInstanceState.getInt("selectedAccountIndex"));

        setupButtonListeners();
    }

    public void onSaveButtonClicked() {
        Intent resultIntent = new Intent();

        TextInputFragment accountNameInputFragment = binding.accountNameInputFragment.getFragment();
        String accountName = accountNameInputFragment.getUserInput();
        AccountType accountType = AccountType.values()[binding.accountTypeDropdown.getSelectedItemPosition()];
        MonetaryAmount startingMonetaryAmount = ((MonetaryAmountInputFragment) binding.amountInputFragment.getFragment()).getAmount();

        Account account = new Account(accountName, accountType, startingMonetaryAmount);

        if (accountType != AccountType.CASH) {
            String bankName = ((TextInputFragment) binding.bankNameInputFragment.getFragment()).getUserInput();
            account.setBankName(bankName);
        }

        if (accountType == AccountType.CREDIT_CARD) {
            CreditCardDetails creditCardDetails = ((CreditCardDetailsFragment) binding.creditCardDetailsFragment.getFragment()).getCreditCardDetails();
            MonetaryAmount creditCardLimit = ((MonetaryAmountInputFragment) binding.creditCardLimitFragment.getFragment()).getAmount();
            creditCardDetails.setCreditLimit(creditCardLimit);
            resultIntent.putExtra("creditCardDetails", creditCardDetails);
        }

        resultIntent.putExtra("account", account);

        if (accountName.trim().isEmpty()) {
            Snackbar.make(binding.saveButton, R.string.form_not_completed, Snackbar.LENGTH_SHORT).show();
            accountNameInputFragment.triggerError();
        } else {
            setResult(-1, resultIntent);
            finish();
        }
    }

    public void onAbortButtonClicked() {
        setResult(0);
        finish();
    }
}