package de.efischer.financetracker.common.inputs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import de.efischer.financetracker.databinding.FragmentTextInputBinding;


public class TextInputFragment extends Fragment {

    public static final String DESCRIPTION_TEXT_KEY = "DESCRIPTION_TEXT";
    public static final String TEXTFIELD_HINT_KEY = "TEXTFIELD_HINT";

    private String userInput;
    private FragmentTextInputBinding binding;

    public TextInputFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTextInputBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        int textId = requireArguments().getInt(DESCRIPTION_TEXT_KEY);
        int textFieldHint = requireArguments().getInt(TEXTFIELD_HINT_KEY);

        binding.userInput.setText(textId);
        binding.descriptionField.setHint(textFieldHint);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}