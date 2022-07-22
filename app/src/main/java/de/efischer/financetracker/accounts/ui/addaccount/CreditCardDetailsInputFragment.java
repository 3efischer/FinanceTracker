package de.efischer.financetracker.accounts.ui.addaccount;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import de.efischer.financetracker.R;
import de.efischer.financetracker.accounts.model.entities.CreditCardDetails;
import de.efischer.financetracker.accounts.model.valueobjects.CreditCardType;
import de.efischer.financetracker.common.inputs.NumberInputFragment;
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

        setupCreditCardDropdown();
        setupCreditCardNumberField();
    }

    private void setupCreditCardDropdown() {
        CreditCardType[] creditCardTypes = CreditCardType.values();
        binding.creditCardTypeDropdown.setAdapter(new DropdownAdapter(this.getContext(), R.layout.account_type_dropdown_item, creditCardTypes));
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

        return this.creditCardDetails;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("selectedCreditCardTypeIndex", binding.creditCardTypeDropdown.getSelectedItemPosition());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            setupCreditCardDropdown();
            binding.creditCardTypeDropdown.setSelection(savedInstanceState.getInt("selectedCreditCardTypeIndex"));
        }
    }
}