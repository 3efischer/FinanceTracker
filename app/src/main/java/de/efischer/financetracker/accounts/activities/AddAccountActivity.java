package de.efischer.financetracker.accounts.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import de.efischer.financetracker.R;
import de.efischer.financetracker.accounts.fragments.AccountDropdownAdapter;
import de.efischer.financetracker.accounts.model.valueobjects.AccountType;
import de.efischer.financetracker.accounts.model.valueobjects.CreditCardType;
import de.efischer.financetracker.common.inputs.TextInputFragment;

public class AddAccountActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);

        if (savedInstanceState == null) {
            setupAccountDropdown();
            setupCreditCardDropdown();
            setupNameField();

        }
    }

    private void setupNameField() {
        Bundle args = new Bundle();
        args.putInt(TextInputFragment.DESCRIPTION_TEXT_KEY, R.string.account_name);
        args.putInt(TextInputFragment.TEXTFIELD_HINT_KEY, R.string.account_name);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.account_name_input_fragment, TextInputFragment.class, args)
                .commit();
    }

    private void setupAccountDropdown() {
        AccountType[] accountTypes = AccountType.values();
        String[] accountTypesAsStrings = new String[accountTypes.length];
        Integer[] imageIdArray = new Integer[accountTypes.length];

        for(int i = 0; i < accountTypes.length; i++) {
            accountTypesAsStrings[i] = getResources().getString(accountTypes[i].name);
            imageIdArray[i] = accountTypes[i].iconId;
        }

        Spinner spinner = findViewById(R.id.account_type_dropdown);
        spinner.setAdapter(new AccountDropdownAdapter(this, R.layout.account_type_dropdown_item, accountTypesAsStrings, imageIdArray));
        spinner.setOnItemSelectedListener(this);
    }

    private void setupCreditCardDropdown() {
        CreditCardType[] creditCardTypes = CreditCardType.values();
        String[] creditCardTypesAsStrings = new String[creditCardTypes.length];
        Integer[] imageIdArray = new Integer[creditCardTypes.length];

        for(int i = 0; i < creditCardTypes.length; i++) {
            creditCardTypesAsStrings[i] = getResources().getString(creditCardTypes[i].nameId);
            imageIdArray[i] = creditCardTypes[i].iconId;
        }

        Spinner spinner = findViewById(R.id.credit_card_type_dropdown);
        spinner.setAdapter(new AccountDropdownAdapter(this, R.layout.account_type_dropdown_item, creditCardTypesAsStrings, imageIdArray));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            AccountType[] accountTypes = AccountType.values();

            Spinner creditCardTypeSpinner = findViewById(R.id.credit_card_type_dropdown);
            creditCardTypeSpinner.setVisibility(accountTypes[position] == AccountType.CREDIT_CARD ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}