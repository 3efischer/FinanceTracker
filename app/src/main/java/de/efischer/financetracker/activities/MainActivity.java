package de.efischer.financetracker.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import de.efischer.financetracker.R;
import de.efischer.financetracker.fragments.AccountOverviewFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set initial view
        setAccountOverviewFragment();
    }

    private void setAccountOverviewFragment() {
        if(fragmentManager == null) {
            fragmentManager = getSupportFragmentManager();
        }

        Fragment accountOverview = new AccountOverviewFragment();
        fragmentManager.beginTransaction().add(R.id.fragment_content, accountOverview).commit();
    }


    public void addAccount(View view) {
        Intent intent = new Intent(this, AddAccountActivity.class);
        startActivity(intent);
    }
}