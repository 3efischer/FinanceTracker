package de.efischer.financetracker.common.inputs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import de.efischer.financetracker.R;
import de.efischer.financetracker.databinding.FragmentTextInputBinding;


public class TextInputFragment extends Fragment {

    public static final String DESCRIPTION_TEXT_KEY = "DESCRIPTION_TEXT";
    public static final String TEXTFIELD_HINT_KEY = "TEXTFIELD_HINT";
    public static final String INPUT_IS_MANDATORY = "INPUT_IS_MANDATORY";

    private String userInputString;
    private FragmentTextInputBinding binding;
    private boolean isMandatory;

    public TextInputFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentTextInputBinding.inflate(inflater, container, false);
        this.userInputString = "";

        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        int textId = requireArguments().getInt(DESCRIPTION_TEXT_KEY);
        int textFieldHint = requireArguments().getInt(TEXTFIELD_HINT_KEY);
        boolean isMandatory = requireArguments().getBoolean(INPUT_IS_MANDATORY);

        binding.userInput.setHint(textId);
        binding.descriptionField.setHint(textFieldHint);
        this.isMandatory = isMandatory;

        this.binding.userInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {

                userInputString = String.valueOf(this.binding.userInput.getText());

                if (this.isMandatory && userInputString.isEmpty()) {
                    this.binding.textinputErrorLayout.setError(
                            getResources().getString(R.string.field_not_optional));
                    this.binding.textinputErrorLayout.setErrorEnabled(true);
                } else {
                    this.binding.textinputErrorLayout.setErrorEnabled(false);
                }
            }
        });
    }

    public String getUserInput() {
        return this.userInputString;
    }

    public void triggerError() {
        this.binding.textinputErrorLayout.setError(getResources().getString(R.string.field_not_optional));
        this.binding.textinputErrorLayout.setErrorEnabled(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}