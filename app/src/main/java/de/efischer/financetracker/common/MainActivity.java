package de.efischer.financetracker.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import de.efischer.financetracker.accounts.activities.AddAccountActivity;
import de.efischer.financetracker.accounts.model.entities.Account;
import de.efischer.financetracker.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ActivityResultLauncher<Intent> addAccountResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Callback for AddAccount
        this.addAccountResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Log.println(Log.INFO, null, "New account created.");

                        assert result.getData() != null;
                        Account account = (Account) result.getData().getSerializableExtra("account");

                    } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                        Log.println(Log.INFO, null, "Adding account aborted.");
                    }
                }
        );

        binding.buttonAdd.setOnClickListener(this::addAccount);
    }

    public void addAccount(View view) {
        Intent intent = new Intent(this, AddAccountActivity.class);
        this.addAccountResultLauncher.launch(intent);
    }
}