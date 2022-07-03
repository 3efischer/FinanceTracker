package de.efischer.financetracker.common.inputs;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.math.BigInteger;
import java.util.regex.Pattern;

import de.efischer.financetracker.R;


public class AmountInputFragment extends Fragment {

    public static final String IS_STARTING_POSITIVE = "IS_STARTING_POSITIVE";
    public static final String AMOUNT_TYPE_TITLE = "AMOUNT_TYPE_TITLE";

    private int integral;
    private int decimal;
    private BigInteger amount;

    public AmountInputFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_amount_input, container, false);

        int titleId = requireArguments().getInt(AMOUNT_TYPE_TITLE);
        TextView amountTitle = view.findViewById(R.id.amount_input_title);
        amountTitle.setText(titleId);

        boolean isPositive = requireArguments().getBoolean(IS_STARTING_POSITIVE);
        ToggleButton toggleButton = view.findViewById(R.id.toggleImage);
        toggleButton.setChecked(isPositive);
        toggleButton.setBackgroundResource(isPositive ? R.drawable.ic_plus : R.drawable.ic_minus);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ToggleButton toggleButton = view.findViewById(R.id.toggleImage);
        toggleButton.setOnClickListener(v ->
                toggleButton.setBackgroundResource(toggleButton.isChecked() ? R.drawable.ic_plus : R.drawable.ic_minus));

        EditText integralTextField = view.findViewById(R.id.integralPartInput);
        EditText decimalTextField = view.findViewById(R.id.decimalPartInput);
        integralTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String inputString = s.toString();

                boolean containsSeparator = inputString.contains(".") || inputString.contains(",");

                if(containsSeparator) {
                    inputString = inputString.replaceAll("\\.", "");
                    inputString = inputString.replaceAll(",", "");
                    decimalTextField.requestFocus();
                }

                boolean isNumber = true;
                if(!inputString.isEmpty()) {
                    Pattern isNumberPattern = Pattern.compile("[1-9][0-9]*$");
                    isNumber = isNumberPattern.matcher(inputString).matches();
                }

                if(!isNumber) {
                    inputString = inputString.replaceAll("[^0-9]+","");
                }

                if(!isNumber || containsSeparator) {
                    integralTextField.setText(inputString);
                    integralTextField.setSelection(inputString.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}