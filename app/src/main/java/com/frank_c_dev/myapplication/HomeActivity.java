package com.frank_c_dev.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.frank_c_dev.myapplication.databinding.ActivityHomeBinding;
import com.google.gson.Gson;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Gson gson = new Gson();
        AccountEntity accountEntity = gson.fromJson(getIntent().getStringExtra("userData"), AccountEntity.class);

        if(accountEntity != null) {
            binding.titleTextView.setText(String.format("Bienvenido %s", accountEntity.getUsername()));
            binding.userTextView.setText(accountEntity.getUsername());
            binding.nameTextView.setText(String.format("%s %s", accountEntity.getFirstName(), accountEntity.getLastName()));
            binding.emailTextView.setText(accountEntity.getEmail());
            binding.phoneTextView.setText(accountEntity.getPhone());
        }
    }
}