package de.efischer.financetracker.common.inputs;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import de.efischer.financetracker.databinding.FragmentNumberInputBinding;

public class NumberInputFragment extends Fragment {

    public static final String DESCRIPTION_TEXT_KEY = "DESCRIPTION_TEXT";
    public static final String TEXTFIELD_HINT_KEY = "TEXTFIELD_HINT";
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

        binding.userInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                userInput = String.valueOf(binding.userInput.getText());
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        int textId = requireArguments().getInt(DESCRIPTION_TEXT_KEY);
        int hintId = requireArguments().getInt(TEXTFIELD_HINT_KEY);

        binding.descriptionField.setHint(textId);
        binding.userInput.setHint(hintId);

        binding.userInput.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(requireArguments().getInt(MAX_LENGTH_KEY)) {
                }});
    }

    public String getUserInput() {
        return this.userInput;
    }
}