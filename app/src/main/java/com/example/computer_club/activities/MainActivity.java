package com.example.computer_club.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.computer_club.R;
import com.example.computer_club.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavHostFragment hostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.host_fragment);

        NavigationUI.setupWithNavController(binding.bottomNav, hostFragment.getNavController());

    }
}