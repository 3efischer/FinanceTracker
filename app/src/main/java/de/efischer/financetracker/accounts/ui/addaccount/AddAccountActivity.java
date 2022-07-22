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
import de.efischer.financetracker.accounts.model.valueobjects.Amount;
import de.efischer.financetracker.common.inputs.AmountInputFragment;
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
        }
    }

    private void setupButtonListeners() {
        binding.saveButton.setOnClickListener(onClick -> onSaveButtonClicked());
        binding.abortButton.setOnClickListener(onClick -> onAbortButtonClicked());
    }

    private void setupNameField() {
        Bundle args = new Bundle();
        args.putInt(TextInputFragment.DESCRIPTION_TEXT_KEY, R.string.account_name);
        args.putInt(TextInputFragment.TEXTFIELD_HINT_KEY, R.string.account_name_description);
        args.putBoolean(TextInputFragment.INPUT_IS_MANDATORY, true);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.account_name_input_fragment, TextInputFragment.class, args)
                .commit();
    }

    private void setupAccountTypeChooser() {
        AccountType[] accountTypes = AccountType.values();
        String[] accountTypesAsStrings = new String[accountTypes.length];
        Integer[] imageIdArray = new Integer[accountTypes.length];

        for (int i = 0; i < accountTypes.length; i++) {
            accountTypesAsStrings[i] = getResources().getString(accountTypes[i].name);
            imageIdArray[i] = accountTypes[i].iconId;
        }

        binding.accountTypeDropdown.setAdapter(new DropdownAdapter(this, R.layout.account_type_dropdown_item, accountTypesAsStrings, imageIdArray));
        binding.accountTypeDropdown.setOnItemSelectedListener(createDropdownListener());
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
        args.putBoolean(AmountInputFragment.IS_STARTING_POSITIVE, true);
        args.putInt(AmountInputFragment.AMOUNT_TYPE_TITLE, R.string.initial_account_balance);
        args.putBoolean(AmountInputFragment.SHOW_CURRENCY_LIST, true);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.amount_input_fragment, AmountInputFragment.class, args)
                .commit();
    }

    private void setupCreditCardLimitField() {
        Bundle args = new Bundle();
        args.putInt(AmountInputFragment.AMOUNT_TYPE_TITLE, R.string.credit_card_limit);
        args.putBoolean(AmountInputFragment.IS_STARTING_POSITIVE, false);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.credit_card_limit_fragment, AmountInputFragment.class, args)
                .commit();
    }

    private void setupBankNameField() {
        Bundle args = new Bundle();
        args.putInt(TextInputFragment.DESCRIPTION_TEXT_KEY, R.string.bank_name);
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
        TextInputFragment accountNameInputFragment = binding.accountNameInputFragment.getFragment();

        String accountName = accountNameInputFragment.getUserInput();
        AccountType accountType = AccountType.values()[binding.accountTypeDropdown.getSelectedItemPosition()];
        Amount startingAmount = ((AmountInputFragment) binding.amountInputFragment.getFragment()).getAmount();

        String bankName = ((TextInputFragment) binding.bankNameInputFragment.getFragment()).getUserInput();

        CreditCardDetails creditCardDetails = ((CreditCardDetailsInputFragment) binding.creditCardDetailsFragment.getFragment()).getCreditCardDetails();
        Amount creditCardLimit = ((AmountInputFragment) binding.creditCardLimitFragment.getFragment()).getAmount();

        creditCardDetails.setCreditLimit(creditCardLimit);

        Account account = new Account(accountName, accountType, startingAmount);

        if (bankName != null) {
            account.setBankName(bankName);
        }

        if (accountName == null || accountName.isEmpty()) {
            Snackbar.make(binding.saveButton, R.string.form_not_completed, Snackbar.LENGTH_SHORT).show();
            accountNameInputFragment.triggerError();
        } else {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("account", account);
            resultIntent.putExtra("creditCardDetails", creditCardDetails);

            setResult(-1, resultIntent);
            finish();
        }
    }

    public void onAbortButtonClicked() {
        setResult(0);
        finish();
    }
}