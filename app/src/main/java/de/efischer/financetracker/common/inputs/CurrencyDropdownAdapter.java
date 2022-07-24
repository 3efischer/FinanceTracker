package de.efischer.financetracker.common.inputs;

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
import java.util.Locale;

import de.efischer.financetracker.R;

public class CurrencyDropdownAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<Currency> currenciesToDisplay;
    private int indexOfLocalCurrency;

    public CurrencyDropdownAdapter(Context context, int resource) {

        super(context, R.layout.currency_list_item, R.id.currency_name);
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

    public int getLocalCurrencyIndex() {
        return this.indexOfLocalCurrency;
    }


    private void setupCurrencies() {
        String[] countryCodes = context.getResources().getStringArray(R.array.country_codes);

        Locale locale = context.getResources().getConfiguration().getLocales().get(0);
        Currency localCurrency = Currency.getInstance(locale);

        for (int i = 0; i < countryCodes.length; i++) {
            Currency currency = Currency.getInstance(countryCodes[i]);

            if (currency == localCurrency) {
                indexOfLocalCurrency = i;
            }

            currenciesToDisplay.add(currency);
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
