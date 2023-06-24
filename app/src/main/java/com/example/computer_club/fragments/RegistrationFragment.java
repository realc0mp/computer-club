package com.example.computer_club.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.computer_club.databinding.FragmentAuthorBinding;
import com.example.computer_club.databinding.FragmentRegistrationBinding;

public class RegistrationFragment extends Fragment {

    FragmentRegistrationBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRegistrationBinding.inflate(inflater);

        return binding.getRoot();
    }

}