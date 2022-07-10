package de.efischer.financetracker.accounts.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import de.efischer.financetracker.R;
import de.efischer.financetracker.accounts.fragments.creation.AccountDropdownAdapter;
import de.efischer.financetracker.accounts.fragments.creation.CurrencyDropdownAdapter;
import de.efischer.financetracker.accounts.model.valueobjects.AccountType;
import de.efischer.financetracker.common.inputs.AmountInputFragment;
import de.efischer.financetracker.common.inputs.TextInputFragment;
import de.efischer.financetracker.databinding.ActivityAddAccountBinding;

public class AddAccountActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ActivityAddAccountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            setupAccountDropdown();
            setupAmountField();
            setupNameField();
            setupBankNameField();
            setupCurrencyChooser();
            setupCreditCardLimitField();
        }
    }

    private void setupNameField() {
        Bundle args = new Bundle();
        args.putInt(TextInputFragment.DESCRIPTION_TEXT_KEY, R.string.account_name);
        args.putInt(TextInputFragment.TEXTFIELD_HINT_KEY, R.string.account_name_description);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.account_name_input_fragment, TextInputFragment.class, args)
                .commit();
    }

    private void setupAccountDropdown() {
        AccountType[] accountTypes = AccountType.values();
        String[] accountTypesAsStrings = new String[accountTypes.length];
        Integer[] imageIdArray = new Integer[accountTypes.length];

        for (int i = 0; i < accountTypes.length; i++) {
            accountTypesAsStrings[i] = getResources().getString(accountTypes[i].name);
            imageIdArray[i] = accountTypes[i].iconId;
        }

        Spinner spinner = binding.accountTypeDropdown;
        spinner.setAdapter(new AccountDropdownAdapter(this, R.layout.account_type_dropdown_item, accountTypesAsStrings, imageIdArray));
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        AccountType[] accountTypes = AccountType.values();

        Fragment creditCardDetailsFragment = binding.creditCardDetailsFragment.getFragment();
        Fragment creditCardLimitFragment = binding.creditCardLimitFragment.getFragment();
        Fragment bankNameFragment = binding.bankNameInputFragment.getFragment();

        assert (creditCardDetailsFragment != null);
        assert (creditCardLimitFragment != null);
        assert (bankNameFragment != null);


        if (accountTypes[position] == AccountType.CREDIT_CARD) {
            getSupportFragmentManager().beginTransaction().show(creditCardDetailsFragment).commit();
            getSupportFragmentManager().beginTransaction().show(creditCardLimitFragment).commit();
        } else {
            getSupportFragmentManager().beginTransaction().hide(creditCardDetailsFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(creditCardLimitFragment).commit();
        }

        if (accountTypes[position] == AccountType.BANK || accountTypes[position] == AccountType.SAVINGS) {
            getSupportFragmentManager().beginTransaction().show(bankNameFragment).commit();
        } else {
            getSupportFragmentManager().beginTransaction().hide(bankNameFragment).commit();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // do nothing
    }

    private void setupAmountField() {
        Bundle args = new Bundle();
        args.putBoolean(AmountInputFragment.IS_STARTING_POSITIVE, true);
        args.putInt(AmountInputFragment.AMOUNT_TYPE_TITLE, R.string.initial_account_balance);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.amount_input_fragment, AmountInputFragment.class, args)
                .commit();
    }

    private void setupCreditCardLimitField() {
        Bundle args = new Bundle();
        args.putInt(AmountInputFragment.AMOUNT_TYPE_TITLE, R.string.credit_card_limit);
        args.putBoolean(AmountInputFragment.IS_STARTING_POSITIVE, false);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.credit_card_limit_fragment, AmountInputFragment.class, args)
                .commit();
    }

    private void setupCurrencyChooser() {
        Spinner spinner = binding.currencyDropdown;
        spinner.setAdapter(new CurrencyDropdownAdapter(this, R.layout.currency_list_item));
    }

    private void setupBankNameField() {
        Bundle args = new Bundle();
        args.putInt(TextInputFragment.DESCRIPTION_TEXT_KEY, R.string.bank_name);
        args.putInt(TextInputFragment.TEXTFIELD_HINT_KEY, R.string.bank_name);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.bank_name_input_fragment, TextInputFragment.class, args)
                .commit();
    }
}