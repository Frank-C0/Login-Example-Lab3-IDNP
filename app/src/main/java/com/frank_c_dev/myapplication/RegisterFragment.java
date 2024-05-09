package com.frank_c_dev.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.frank_c_dev.myapplication.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;
    private UserManager userManager;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userManager = new UserManager(requireContext());

        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = binding.inputFirstName.getText().toString();
                String lastName = binding.inputLastName.getText().toString();
                String email = binding.inputEmail.getText().toString();
                String phone = binding.inputPhone.getText().toString();
                String password = binding.inputPassword.getText().toString();

                // Registrar el usuario
                userManager.registerUser(new User(firstName, lastName, email, phone, password));
                Toast.makeText(requireContext(), "User registered successfully", Toast.LENGTH_SHORT).show();

                // Navegar al fragment de login después del registro
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}