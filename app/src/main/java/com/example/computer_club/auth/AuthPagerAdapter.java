package com.example.computer_club.auth;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class AuthPagerAdapter extends FragmentStateAdapter {

    public AuthPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case(0):
                return new EnterFragment();
            case(1):
                return new RegistrationFragment();
        }

        return new EnterFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
