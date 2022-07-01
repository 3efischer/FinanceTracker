package de.efischer.financetracker.common.inputs;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import de.efischer.financetracker.R;

public class NumberInputFragment extends Fragment {

    public static String DESCRIPTION_TEXT_KEY = "DESCRIPTION_TEXT";
    public static String TEXTFIELD_HINT_KEY = "TEXTFIELD_HINT";
    public static String MAX_LENGTH_KEY = "MAX_LENGTH_KEY";

    private String userInput;

    public NumberInputFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View textInputFragment = inflater.inflate(R.layout.fragment_number_input, container, false);
        return textInputFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        int textId = requireArguments().getInt(DESCRIPTION_TEXT_KEY);

        // Set description in description field
        TextView textField = view.findViewById(R.id.description_field);
        textField.setText(textId);

        // Set text hint in textfield
        EditText editText = view.findViewById(R.id.user_input);
        editText.setHint(requireArguments().getInt(TEXTFIELD_HINT_KEY));

        // Set max length
        editText.setFilters(new InputFilter[] {
                new InputFilter.LengthFilter(requireArguments().getInt(MAX_LENGTH_KEY)) {
        }});
    }
}