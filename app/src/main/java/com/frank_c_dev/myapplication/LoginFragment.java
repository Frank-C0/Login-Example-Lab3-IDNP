package com.frank_c_dev.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.frank_c_dev.myapplication.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private UserManager userManager;

    public static Fragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userManager = new UserManager(requireContext());

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.inputUserName.getText().toString();
                String password = binding.inputPassword.getText().toString();
                if (userManager.loginUser(email, password)) {
                    // Login exitoso
                    String fullName = userManager.getUserFullName(email);
                    Toast.makeText(requireContext(), "Welcome, " + fullName, Toast.LENGTH_SHORT).show();
                    // Podria abrirse otra actividad o otro fragment
                } else {
                    // Login fallido
                    binding.incorrectAuth.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar al fragmento de registro
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, RegisterFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}