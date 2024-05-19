package com.frank_c_dev.myapplication;

import static com.frank_c_dev.myapplication.AccountActivity.USER_DATA;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.frank_c_dev.myapplication.databinding.ActivityLoginBinding;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {
    public final static String TAG = "LoginActivity";
    private ActivityLoginBinding binding;
    private ActivityResultLauncher<Intent> activityResultLaunch;
    private AccountEntity savedAccountEntity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Inicializar el ActivityResultLauncher para recibir el resultado de la actividad de registro
        activityResultLaunch = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    int resultCode = result.getResultCode();
                    Log.d(TAG, "onActivityResult: " + resultCode);

                    if (resultCode == AccountActivity.RESULT_ACCOUNT_REGISTER_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Gson gson = new Gson();
                            savedAccountEntity = gson.fromJson(data.getStringExtra(USER_DATA), AccountEntity.class);
                        }
                    } else if (resultCode == AccountActivity.RESULT_ACCOUNT_REGISTER_CANCELED) {
                        Toast.makeText(
                                getApplicationContext(),
                                "Registro cancelado",
                                Toast.LENGTH_LONG).show();
                    }
                });

        // Cuando se presiona el botón de registro, se lanza la actividad de registro con el ActivityResultLauncher
        View.OnClickListener registerLinkListener = v -> {
            Intent intent = new Intent(LoginActivity.this, AccountActivity.class);
            activityResultLaunch.launch(intent);
        };

        binding.registerLink.setOnClickListener(registerLinkListener);
        binding.goToRegistrationButton.setOnClickListener(registerLinkListener);

        binding.loginButton.setOnClickListener(v -> {
            String username = binding.inputUserName.getText().toString();
            String password = binding.inputPassword.getText().toString();

            if (savedAccountEntity != null && savedAccountEntity.getUsername().equals(username)
                    && savedAccountEntity.getPassword().equals(password)) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                Gson gson = new Gson();
                intent.putExtra(USER_DATA, gson.toJson(savedAccountEntity));

                startActivity(intent);
            } else {
                Toast.makeText(
                        LoginActivity.this,
                        "Usuario o contraseña incorrectos",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LoginActivity", "onPause !!!!!!!!!!!!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LoginActivity", "onStop !!!!!!!!!!!!");
    }
}