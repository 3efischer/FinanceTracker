package de.efischer.financetracker.accounts.fragments.creation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import de.efischer.financetracker.R;

public class CurrencyDropdownAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<Currency> currenciesToDisplay;

    public CurrencyDropdownAdapter(Context context, int resource) {

        super(context,  R.layout.currency_list_item, R.id.currency_name);
        this.context = context;

        currenciesToDisplay = new ArrayList<>();
        setupCurrencies();
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }


    private void setupCurrencies() {
        String[] countryCodes = context.getResources().getStringArray(R.array.country_codes);

        for (String countryCode : countryCodes) {
            currenciesToDisplay.add(Currency.getInstance(countryCode));
        }

        super.addAll(countryCodes);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.currency_list_item, parent, false);
        Currency currency = currenciesToDisplay.get(position);

        TextView currencyNameView = item.findViewById(R.id.currency_name);
        String currencyNameFull = currency.getDisplayName() + " (" + currency.getSymbol() + ")";
        currencyNameView.setText(currencyNameFull);

        TextView currencySymbolView = item.findViewById(R.id.currency_symbol);
        currencySymbolView.setText(currency.getCurrencyCode());

        return item;
    }
}
