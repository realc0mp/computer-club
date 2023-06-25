package com.example.computer_club.fragments;

import static com.example.computer_club.EmailValidator.isEmailValid;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.computer_club.EmailValidator;
import com.example.computer_club.User;
import com.example.computer_club.databinding.FragmentAuthorBinding;
import com.example.computer_club.databinding.FragmentRegistrationBinding;
import com.example.computer_club.viewmodels.RegisterViewModel;

public class RegistrationFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    FragmentRegistrationBinding binding;
    RegisterViewModel viewModel;

    String date = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRegistrationBinding.inflate(inflater);

        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        binding.registerBtn.setOnClickListener(v -> register());

        binding.datePickBtn.setOnClickListener(v -> new DatePickerDialog(
                        requireContext(), this, 2022, 11, 11
                ).show()
        );

        viewModel.error.observe(getViewLifecycleOwner(), this::toast);

        return binding.getRoot();
    }

    private void register() {
        String name = binding.name.getText().toString();
        String password = binding.password.getText().toString();
        String email = binding.email.getText().toString();

        if (name.length() == 0 || email.length() == 0 || password.length() == 0 || date.length() == 0) {
            toast("Заполните поля!");
            return;
        }

        if (!isEmailValid(email)) {
            toast("Почта введена неверно!");
            return;
        }

        if (!password.equals(binding.repeatPassword.getText().toString())) {
            toast("Пароли не совпадают");
            return;
        }


        viewModel.register(new User(
                        name,
                        date,
                        email,
                        password
                )
        );
    }

    private void toast(String text) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date = ("" + dayOfMonth + '.' + month + '.' + year);
        binding.datePickBtn.setText(date);
    }
}