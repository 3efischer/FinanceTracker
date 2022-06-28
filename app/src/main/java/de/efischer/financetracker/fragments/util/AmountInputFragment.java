package de.efischer.financetracker.fragments.util;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import de.efischer.financetracker.R;


public class AmountInputFragment extends Fragment {

    private boolean isPositive;
    private int decimal;

    public AmountInputFragment() {
        // Required empty public constructor
    }

    public AmountInputFragment(boolean startWithPositiveInput) {
        this.isPositive = startWithPositiveInput;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_amount_input, container, false);
    }
}