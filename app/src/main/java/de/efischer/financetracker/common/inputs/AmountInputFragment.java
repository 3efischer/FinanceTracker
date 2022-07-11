package de.efischer.financetracker.common.inputs;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Currency;
import java.util.regex.Pattern;

import de.efischer.financetracker.R;
import de.efischer.financetracker.accounts.fragments.creation.CurrencyDropdownAdapter;
import de.efischer.financetracker.accounts.model.valueobjects.Amount;
import de.efischer.financetracker.databinding.FragmentAmountInputBinding;


public class AmountInputFragment extends Fragment {

    public static final String IS_STARTING_POSITIVE = "IS_STARTING_POSITIVE";
    public static final String AMOUNT_TYPE_TITLE = "AMOUNT_TYPE_TITLE";
    public static final String SHOW_CURRENCY_LIST = "SHOW_CURRENCY_LIST";

    private int integral;
    private int decimal;
    private BigInteger amount;

    private FragmentAmountInputBinding binding;

    public AmountInputFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAmountInputBinding.inflate(inflater, container, false);

        int titleId = requireArguments().getInt(AMOUNT_TYPE_TITLE);
        binding.amountInputTitle.setText(titleId);

        boolean isPositive = requireArguments().getBoolean(IS_STARTING_POSITIVE);
        binding.toggleImage.setChecked(isPositive);
        binding.toggleImage.setBackgroundResource(isPositive ? R.drawable.ic_plus : R.drawable.ic_minus);

        setListeners();
        setupCurrencyChooser();

        return binding.getRoot();
    }

    private void setListeners() {

        binding.integralPartInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                String inputText = String.valueOf(binding.integralPartInput.getText());
                integral = Integer.parseInt(inputText);
            }
        });

        binding.decimalPartInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                String inputText = String.valueOf(binding.decimalPartInput.getText());
                decimal = Integer.parseInt(inputText);
            }
        });
    }

    private void setupCurrencyChooser() {
        Spinner spinner = binding.currencyDropdown;
        spinner.setAdapter(new CurrencyDropdownAdapter(this.getContext(), R.layout.currency_list_item));

        boolean showCurrencyList = requireArguments().getBoolean(SHOW_CURRENCY_LIST);
        if (showCurrencyList) {
            binding.currencyDropdown.setVisibility(View.VISIBLE);
            binding.currencyDescriptionText.setVisibility(View.VISIBLE);
        } else {
            binding.currencyDropdown.setVisibility(View.GONE);
            binding.currencyDescriptionText.setVisibility(View.GONE);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.toggleImage.setOnClickListener(v ->
                binding.toggleImage.setBackgroundResource(
                        binding.toggleImage.isChecked() ? R.drawable.ic_plus : R.drawable.ic_minus));

        binding.integralPartInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String inputString = s.toString();

                Pattern containsSeparatorPattern = Pattern.compile("^\\d*[\\\\.,]+\\d*$");
                boolean containsSeparator = containsSeparatorPattern.matcher(inputString).matches();

                Pattern isNumberPattern = Pattern.compile("^$|^0$|^[1-9][0-9]*$");
                boolean isNumber = isNumberPattern.matcher(inputString).matches();

                if (!isNumber || containsSeparator) {
                    inputString = inputString.replaceAll("[^0-9]+", "");
                    inputString = Integer.toString(Integer.parseInt(inputString));
                    binding.integralPartInput.setText(inputString);
                    binding.integralPartInput.setSelection(inputString.length());
                }

                if (containsSeparator) {
                    binding.decimalPartInput.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.println(Log.INFO, null, "AfterTextChanged");
            }
        });
    }

    public Amount getAmount() {
        String currencyString = (String) binding.currencyDropdown.getSelectedItem();
        Currency selectedCurrency = Currency.getInstance(currencyString);

        String integralAsString = String.valueOf(binding.integralPartInput.getText());
        if (integralAsString.isEmpty()) {
            this.integral = 0;
        } else {
            this.integral = Integer.parseInt(integralAsString);
        }

        String decimalAsString = String.valueOf(binding.decimalPartInput.getText());
        if (decimalAsString.isEmpty()) {
            this.decimal = 0;
        } else {
            this.decimal = Integer.parseInt(decimalAsString);
        }

        BigDecimal amountAsBigDecimal = new BigDecimal(integral + "." + decimal);


        if(!binding.toggleImage.isChecked()) {
            amountAsBigDecimal = amountAsBigDecimal.negate();
        }

        return Amount.of(amountAsBigDecimal, selectedCurrency);
    }
}