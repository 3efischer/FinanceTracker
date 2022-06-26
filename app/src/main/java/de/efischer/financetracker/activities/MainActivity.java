package de.efischer.financetracker.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import de.efischer.financetracker.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addAccount(View view) {
        Intent intent = new Intent(this, AddAccountActivity.class);
        startActivity(intent);
    }
}