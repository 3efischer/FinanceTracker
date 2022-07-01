package de.efischer.financetracker.accounts.fragments.creation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import de.efischer.financetracker.R;

public class AccountDropdownAdapter extends ArrayAdapter<String> {

    private Context context;
    private String[] accountTypeStrings;
    private Integer[] imageIds;

    public AccountDropdownAdapter(Context context, int resource, String[] objects,
                          Integer[] imageArray) {

        super(context,  R.layout.account_type_dropdown_item, R.id.account_type_name, objects);
        this.context = context;
        this.accountTypeStrings = objects;
        this.imageIds = imageArray;
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

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.account_type_dropdown_item, parent, false);

        TextView accountTypeView = item.findViewById(R.id.account_type_name);
        accountTypeView.setText(accountTypeStrings[position]);

        ImageView accountIconView = item.findViewById(R.id.account_type_icon);
        accountIconView.setBackgroundResource((int) imageIds[position]);

        return item;
    }
}
