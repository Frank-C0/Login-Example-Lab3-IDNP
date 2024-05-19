package com.frank_c_dev.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.frank_c_dev.myapplication.databinding.ActivityAccountBinding;
import com.google.gson.Gson;

public class AccountActivity extends AppCompatActivity {
    public static final String TAG = "AccountActivity";
    public static final String USER_DATA = "userData";
    public static final int RESULT_ACCOUNT_REGISTER_OK = 400;
    public static final int RESULT_ACCOUNT_REGISTER_CANCELED = 401;
    private ActivityAccountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountEntity accountEntity = new AccountEntity(
                        binding.inputFirstName.getText().toString(),
                        binding.inputLastName.getText().toString(),
                        binding.inputEmail.getText().toString(),
                        binding.inputPhone.getText().toString(),
                        binding.inputUserName.getText().toString(),
                        binding.inputPassword.getText().toString()
                );

                Intent returnIntent = new Intent();
                Gson gson = new Gson();
                String json = gson.toJson(accountEntity);
                returnIntent.putExtra(USER_DATA, json);
                Toast.makeText(AccountActivity.this, "Usuario registrado exitosamente", Toast.LENGTH_LONG).show();
                Log.d(TAG, "onClick: " + json);
                setResult(RESULT_ACCOUNT_REGISTER_OK, returnIntent);
                finish();
            }
        });

        binding.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_ACCOUNT_REGISTER_CANCELED);
                finish();
            }
        });
    }
}