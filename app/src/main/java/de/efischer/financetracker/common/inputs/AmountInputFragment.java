package de.efischer.financetracker.common.inputs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.math.BigInteger;

import de.efischer.financetracker.R;


public class AmountInputFragment extends Fragment {

    public static final String IS_STARTING_POSITIVE = "IS_STARTING_POSITIVE";
    public static final String AMOUNT_TYPE_TITLE = "AMOUNT_TYPE_TITLE";

    private boolean isPositive;
    private int titleId;
    private int integral;
    private int decimal;
    private BigInteger amount;

    public AmountInputFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.isPositive = requireArguments().getBoolean(IS_STARTING_POSITIVE);
        this.titleId = requireArguments().getInt(AMOUNT_TYPE_TITLE);
        return inflater.inflate(R.layout.fragment_amount_input, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView inputFieldTitle = view.findViewById(R.id.amount_input_type);
        inputFieldTitle.setText(titleId);

        ImageView signToggleImage = view.findViewById(R.id.toggleImage);
        signToggleImage.setBackgroundResource(this.isPositive ? R.drawable.ic_plus : R.drawable.ic_minus);

        signToggleImage.setOnClickListener(v -> {
            isPositive = !isPositive;
            signToggleImage.setBackgroundResource(isPositive ? R.drawable.ic_plus : R.drawable.ic_minus);
        });
    }
}