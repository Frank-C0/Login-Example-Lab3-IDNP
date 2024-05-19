package com.frank_c_dev.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.frank_c_dev.myapplication.databinding.ActivityLoginAlternativoBinding;

public class LoginAlternativoActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    private ActivityLoginAlternativoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginAlternativoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        EditText editUsername = binding.editUsername;
        EditText editPassword = binding.editPassword;
        Button btnLogin = binding.btnLogin;

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editUsername.getText().toString().equals("admin") && editPassword.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(), "Bienvenido!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Bienvenido");
                } else {
                    Toast.makeText(getApplicationContext(), "Error en la autenticacion", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Error en la autenticacion");
                }
            }
        });

        Button btnRegister = binding.btnRegister;
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterAlternativoActivity.class);
                startActivity(intent);
            }
        });
    }
}