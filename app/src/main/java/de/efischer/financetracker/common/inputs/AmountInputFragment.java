package de.efischer.financetracker.common.inputs;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.math.BigInteger;
import java.util.regex.Pattern;

import de.efischer.financetracker.R;
import de.efischer.financetracker.databinding.FragmentAmountInputBinding;


public class AmountInputFragment extends Fragment {

    public static final String IS_STARTING_POSITIVE = "IS_STARTING_POSITIVE";
    public static final String AMOUNT_TYPE_TITLE = "AMOUNT_TYPE_TITLE";

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

        return binding.getRoot();
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

                if (containsSeparator) {
                    binding.decimalPartInput.requestFocus();
                }

                Pattern isNumberPattern = Pattern.compile("^$|^0$|^[1-9][0-9]*$");
                boolean isNumber = isNumberPattern.matcher(inputString).matches();

                if (!isNumber || containsSeparator) {
                    inputString = inputString.replaceAll("[^0-9]+", "");
                    inputString = Integer.toString(Integer.parseInt(inputString));
                    binding.integralPartInput.setText(inputString);
                    binding.integralPartInput.setSelection(inputString.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}