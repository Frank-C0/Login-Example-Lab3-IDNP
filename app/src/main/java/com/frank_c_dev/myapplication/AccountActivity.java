package com.frank_c_dev.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.frank_c_dev.myapplication.databinding.ActivityAccountBinding;

public class AccountActivity extends AppCompatActivity {
    private ActivityAccountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActivityResultLauncher<Intent> activityResultLaunch = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent data = result.getData();

                            if (data != null) {
                                User user = (User) data.getSerializableExtra("user");

                                if (user != null) {
                                    binding.inputFirstName.setText(user.getFirstName());
                                    binding.inputLastName.setText(user.getLastName());
                                    binding.inputEmail.setText(user.getEmail());
                                    binding.inputPhone.setText(user.getPhone());
                                    binding.inputUserName.setText(user.getUsername());
                                    binding.inputPassword.setText(user.getPassword());
                                }
                            }
                        }
                    }
                });

        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(
                        binding.inputFirstName.getText().toString(),
                        binding.inputLastName.getText().toString(),
                        binding.inputEmail.getText().toString(),
                        binding.inputPhone.getText().toString(),
                        binding.inputUserName.getText().toString(),
                        binding.inputPassword.getText().toString()
                );

                Intent returnIntent = new Intent();
                returnIntent.putExtra("user", user);
                Toast.makeText(AccountActivity.this, "Usuario registrado exitosamente", Toast.LENGTH_LONG).show();

                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });

        binding.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}