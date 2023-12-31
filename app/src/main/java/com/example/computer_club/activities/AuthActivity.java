package com.example.computer_club.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.computer_club.auth.AuthPagerAdapter;
import com.example.computer_club.databinding.ActivityAuthBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class AuthActivity extends AppCompatActivity {

    private ActivityAuthBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        prepareAdapter();
    }

    private void prepareAdapter(){
        binding.pager.setAdapter(new AuthPagerAdapter(this));
        attachMediator();
    }

    private void attachMediator(){

        new TabLayoutMediator(binding.tabs, binding.pager, (tab, position) -> {
            switch (position){
                case(0):
                    tab.setText("Вход");
                    break;
                case(1):
                    tab.setText("Регистрация");
                    break;
            }
        }).attach();
    }

}