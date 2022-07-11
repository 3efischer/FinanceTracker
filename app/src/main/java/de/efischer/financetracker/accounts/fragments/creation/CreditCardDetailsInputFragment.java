package de.efischer.financetracker.accounts.fragments.creation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import de.efischer.financetracker.R;
import de.efischer.financetracker.accounts.model.valueobjects.CreditCardDetails;
import de.efischer.financetracker.accounts.model.valueobjects.CreditCardType;
import de.efischer.financetracker.common.inputs.NumberInputFragment;
import de.efischer.financetracker.common.inputs.TextInputFragment;
import de.efischer.financetracker.databinding.FragmentCreditCardDetailsInputBinding;

public class CreditCardDetailsInputFragment extends Fragment {

    private CreditCardDetails creditCardDetails;
    private FragmentCreditCardDetailsInputBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.creditCardDetails = new CreditCardDetails();
        binding = FragmentCreditCardDetailsInputBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupCreditCardDropdown(view);
        setupCreditCardIssuerField();
        setupCreditCardNumberField();
    }

    private void setupCreditCardDropdown(View view) {
        CreditCardType[] creditCardTypes = CreditCardType.values();
        String[] creditCardTypesAsStrings = new String[creditCardTypes.length];
        Integer[] imageIdArray = new Integer[creditCardTypes.length];

        for (int i = 0; i < creditCardTypes.length; i++) {
            creditCardTypesAsStrings[i] = getResources().getString(creditCardTypes[i].nameId);
            imageIdArray[i] = creditCardTypes[i].iconId;
        }

        Spinner spinner = view.findViewById(R.id.credit_card_type_dropdown);
        spinner.setAdapter(new AccountDropdownAdapter(this.getContext(), R.layout.account_type_dropdown_item, creditCardTypesAsStrings, imageIdArray));
    }

    private void setupCreditCardIssuerField() {
        Bundle args = new Bundle();
        args.putInt(TextInputFragment.DESCRIPTION_TEXT_KEY, R.string.credit_card_issuer);
        args.putInt(TextInputFragment.TEXTFIELD_HINT_KEY, R.string.credit_card_issuer_description);

        getChildFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.credit_card_issuer_input_fragment, TextInputFragment.class, args)
                .commit();
    }

    private void setupCreditCardNumberField() {
        Bundle args = new Bundle();
        args.putInt(NumberInputFragment.DESCRIPTION_TEXT_KEY, R.string.credit_card_number);
        args.putInt(NumberInputFragment.TEXTFIELD_HINT_KEY, R.string.credit_card_number_description);
        args.putInt(NumberInputFragment.MAX_LENGTH_KEY, 4);

        getChildFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.credit_card_number_input_fragment, NumberInputFragment.class, args)
                .commit();
    }

    public CreditCardDetails getCreditCardDetails() {
        CreditCardType creditCardType = CreditCardType.values()[this.binding.creditCardTypeDropdown.getSelectedItemPosition()];
        this.creditCardDetails.setCreditCardType(creditCardType);

        NumberInputFragment creditCardNumberFragment = this.binding.creditCardNumberInputFragment.getFragment();
        this.creditCardDetails.setCreditCardNumber(creditCardNumberFragment.getUserInput());

        TextInputFragment creditCardIssuerFragment = this.binding.creditCardIssuerInputFragment.getFragment();
        this.creditCardDetails.setIssuer(creditCardIssuerFragment.getUserInput());

        return this.creditCardDetails;
    }


}