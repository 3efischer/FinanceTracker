package de.efischer.financetracker.accounts.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import de.efischer.financetracker.R;
import de.efischer.financetracker.accounts.fragments.creation.AccountDropdownAdapter;
import de.efischer.financetracker.accounts.model.valueobjects.AccountType;
import de.efischer.financetracker.common.inputs.TextInputFragment;

public class AddAccountActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);

        if (savedInstanceState == null) {
            setupAccountDropdown();
            setupNameField();
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

        for(int i = 0; i < accountTypes.length; i++) {
            accountTypesAsStrings[i] = getResources().getString(accountTypes[i].name);
            imageIdArray[i] = accountTypes[i].iconId;
        }

        Spinner spinner = findViewById(R.id.account_type_dropdown);
        spinner.setAdapter(new AccountDropdownAdapter(this, R.layout.account_type_dropdown_item, accountTypesAsStrings, imageIdArray));
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            AccountType[] accountTypes = AccountType.values();

            Fragment creditCardDetailsFragment = getSupportFragmentManager().findFragmentById(R.id.credit_card_details_fragment);

            if(accountTypes[position] == AccountType.CREDIT_CARD) {
                getSupportFragmentManager().beginTransaction().show(creditCardDetailsFragment).commit();
            } else {
                getSupportFragmentManager().beginTransaction().hide(creditCardDetailsFragment).commit();
            }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}