package de.efischer.financetracker.common.inputs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import de.efischer.financetracker.R;


public class TextInputFragment extends Fragment {

    public static final String DESCRIPTION_TEXT_KEY = "DESCRIPTION_TEXT";
    public static final String TEXTFIELD_HINT_KEY = "TEXTFIELD_HINT";

    private String userInput;

    public TextInputFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_text_input, container, false);
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
    }
}