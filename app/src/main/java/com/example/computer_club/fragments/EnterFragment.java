package com.example.computer_club.fragments;

import static android.content.Context.MODE_PRIVATE;

import static com.example.computer_club.EmailValidator.isEmailValid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.computer_club.activities.MainActivity;
import com.example.computer_club.databinding.FragmentEnterBinding;
import com.example.computer_club.viewmodels.EnterViewModel;


public class EnterFragment extends Fragment {
    private FragmentEnterBinding binding;
    private EnterViewModel viewModel;

    private SharedPreferences sp;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sp = getContext().getSharedPreferences("APP_PREFS", MODE_PRIVATE);

        if(!sp.getAll().isEmpty()){
            enter();
        }

        viewModel = new ViewModelProvider(this).get(EnterViewModel.class);

        prepareObservers();
        binding = FragmentEnterBinding.inflate(inflater);

        binding.enterBtn.setOnClickListener(v -> enterCheck());


        return binding.getRoot();
    }

    private void prepareObservers(){
        viewModel.enterData.observe(getViewLifecycleOwner(), map -> {
            if(!Boolean.TRUE.equals(map.get("EMAIL_CHECK"))){
                toast("Почта не зарегестрирована");
                return;
            }

            if(!Boolean.TRUE.equals(map.get("PASS_CHECK"))){
                toast("Неверный пароль");
                return;
            }

            putEmail();
            enter();
        });

        viewModel.error.observe(getViewLifecycleOwner(), this::toast);
    }

    private void putEmail(){
        sp.edit().putString("EMAIL", binding.email.getText().toString()).apply();
    }

    private void enter(){
        startActivity(new Intent(requireActivity(), MainActivity.class));
    }

    private void enterCheck(){
        String email = binding.email.getText().toString();
        String pass = binding.password.getText().toString();

        if (email.length() == 0 || pass.length() == 0) {
            toast("Заполните поля!");
            return;
        }

        if(!isEmailValid(email)){
            toast("Почта введена неверно!");
            return;
        }

        viewModel.enter(email, pass);
    }


    private void toast(String text) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show();
    }

}