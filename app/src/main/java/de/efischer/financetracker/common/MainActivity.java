package de.efischer.financetracker.common;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import de.efischer.financetracker.accounts.activities.AddAccountActivity;
import de.efischer.financetracker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    public void addAccount(View view) {
        Intent intent = new Intent(this, AddAccountActivity.class);
        startActivity(intent);

    }
}