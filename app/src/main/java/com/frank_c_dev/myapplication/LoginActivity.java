package com.frank_c_dev.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.frank_c_dev.myapplication.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private ActivityResultLauncher<Intent> activityResultLaunch;

    private User savedUser = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        activityResultLaunch = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        savedUser = (User) (data != null ? data.getSerializableExtra("user") : null);
                    }
                });

        View.OnClickListener registerLinkListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, AccountActivity.class);
                activityResultLaunch.launch(intent);
            }
        };
        binding.registerLink.setOnClickListener(registerLinkListener);
        binding.goToRegistrationButton.setOnClickListener(registerLinkListener);

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.inputUserName.getText().toString();
                String password = binding.inputPassword.getText().toString();

                if (savedUser != null && savedUser.getUsername().equals(username) && savedUser.getPassword().equals(password)) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("user", savedUser);
                    LoginActivity.this.startActivity(intent);
                } else {
                    Toast.makeText(
                            LoginActivity.this,
                            "Usuario o contraseña incorrectos",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}