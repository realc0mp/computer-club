package com.example.computer_club.profile;


import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.computer_club.EmptyListNotificator;
import com.example.computer_club.R;
import com.example.computer_club.activities.AuthActivity;
import com.example.computer_club.databinding.FragmentProfileBinding;
import com.example.computer_club.profile.ProfileViewModel;
import com.example.computer_club.tables.User;

public class ProfileFragment extends Fragment{

    ProfileViewModel viewModel;
    FragmentProfileBinding binding;
    SharedPreferences sp;
    HistoryAdapter adapter;
    User user;
    public ProfileFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater);
        sp = requireContext().getSharedPreferences("APP_PREFS", MODE_PRIVATE);
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        adapter = new HistoryAdapter();

        prepareObservers();

        viewModel.getUserByEmail(sp.getString("EMAIL", " "));

        binding.quiteBtn.setOnClickListener(v -> {
            sp.edit().clear().apply();
            startActivity(new Intent(requireContext(), AuthActivity.class));
        });

        return binding.getRoot();
    }


    private void prepareObservers(){
        viewModel.userData.observe(getViewLifecycleOwner(), user -> {
            this.user = user;
            binding.name.setText(user.name);
            binding.email.setText(user.email);
            binding.date.setText(user.date);
            viewModel.getAllHistory(user.id);
        });

        viewModel.historyData.observe(getViewLifecycleOwner(), histories -> {
            if(histories.size() == 0){
                binding.container.removeAllViews();
                binding.container.addView(EmptyListNotificator.createEmptyListView(
                        binding.container,
                        R.drawable.ic_history,
                        R.string.history_empty
                ));
            } else{
                binding.container.removeAllViews();
                adapter.addItems(histories);
                binding.container.addView(EmptyListNotificator.createRecyclerView(
                        requireContext(),
                        adapter,
                        new LinearLayoutManager(requireContext())
                ));
            }
        });

        viewModel.error.observe(getViewLifecycleOwner(), this::toast);
    }


    private void toast(String text) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show();
    }
}