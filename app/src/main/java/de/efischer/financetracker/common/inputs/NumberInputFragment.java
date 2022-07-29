package de.efischer.financetracker.common.inputs;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import de.efischer.financetracker.databinding.FragmentNumberInputBinding;

public class NumberInputFragment extends Fragment {

    public static final String TEXTFIELD_HINT_KEY = "TEXTFIELD_HINT";
    public static final String TEXTFIELD_HELPER_TEXT_KEY = "TEXTFIELD_HELPER_TEXT_KEY";
    public static final String MAX_LENGTH_KEY = "MAX_LENGTH_KEY";

    private String userInput;
    private FragmentNumberInputBinding binding;

    public NumberInputFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNumberInputBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        int hintId = requireArguments().getInt(TEXTFIELD_HINT_KEY);
        int optionalHelperTextId = requireArguments().getInt(TEXTFIELD_HELPER_TEXT_KEY);

        binding.numberInputLayout.setHint(hintId);

        if (optionalHelperTextId != 0) {
            binding.numberInputLayout.setHelperTextEnabled(true);
            binding.numberInputLayout.setHelperText(getResources().getString(optionalHelperTextId));
        }

        this.userInput = "";

        binding.userInput.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(requireArguments().getInt(MAX_LENGTH_KEY)) {
                }});

        binding.userInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                this.userInput = String.valueOf(binding.userInput.getText());
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (binding.userInput.getText() != null) {
            outState.putString("userInput", binding.userInput.getText().toString());
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            binding.userInput.setText(savedInstanceState.getString("userInput"));
            this.userInput = savedInstanceState.getString("userInput");
        }
    }

    public String getUserInput() {
        return this.userInput;
    }
}