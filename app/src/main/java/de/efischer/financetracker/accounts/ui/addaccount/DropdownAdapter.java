package de.efischer.financetracker.accounts.ui.addaccount;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;

import de.efischer.financetracker.R;

public class DropdownAdapter extends ArrayAdapter<String> implements Serializable {

    private Context context;
    private String[] entriesAsStrings;
    private Integer[] imageIds;

    public DropdownAdapter(Context context, int resource, String[] objects,
                           Integer[] imageArray) {

        super(context, R.layout.account_type_dropdown_item, R.id.account_type_name, objects);
        this.context = context;
        this.entriesAsStrings = objects;
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
        accountTypeView.setText(entriesAsStrings[position]);

        ImageView accountIconView = item.findViewById(R.id.account_type_icon);
        accountIconView.setBackgroundResource(imageIds[position]);

        return item;
    }
}
