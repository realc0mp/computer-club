package com.example.computer_club.main;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.computer_club.DateProvider;
import com.example.computer_club.R;
import com.example.computer_club.databinding.DialogCompBinding;
import com.example.computer_club.tables.Comp;
import com.example.computer_club.tables.Order;

import java.util.ArrayList;
import java.util.Arrays;

public class CompDialog extends DialogFragment {

    private ArrayList<String> timeArr = new ArrayList<>(Arrays.asList("12:00-13:00", "13:00-14:00", "14:00-15:00", "15:00-16:00", "16:00-17:00", "17:00-18:00", "18:00-19:00"));
    private DialogViewModel viewModel;
    private ArrayAdapter<String> spinAdapter;
    private String choseTime = " ";

    private DialogCompBinding binding;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = DialogCompBinding.inflate(getLayoutInflater());
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext(), R.style.MyCustomTheme);
        viewModel = new ViewModelProvider(this).get(DialogViewModel.class);

        assert getArguments() != null;
        Comp comp = (Comp) getArguments().getSerializable("COMP");
        int userId = getArguments().getInt("USER_ID");

        viewModel.getCompOrderedTime(comp.id);

        binding.compName.setText(comp.name);
        binding.compDesc.setText(comp.characteristics);
        binding.compImage.setImageDrawable(AppCompatResources.getDrawable(requireContext(), comp.image));
        binding.dismissDialogBtn.setOnClickListener(v -> dismiss());

        binding.timeSpinner.setOnItemSelectedListener((SimpleOnItemSelected) (parent, view, position, id) -> {
            choseTime = timeArr.get(position);
        });

        binding.addOrderBtn.setOnClickListener(v -> {
            viewModel.addOrder(new Order(choseTime, comp.name, comp.id, userId));
            dismiss();
        });



        builder.setView(binding.getRoot());
        return builder.create();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewModel.orderedTimeData.observe(getViewLifecycleOwner(), dateTimes -> {

            for (int i = 0; i < dateTimes.size(); i++){
                if(dateTimes.get(i).date.equals(DateProvider.getDate())){
                    this.timeArr.remove(dateTimes.get(i).time);
                }
            }

            spinAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, timeArr);
            binding.timeSpinner.setAdapter(spinAdapter);

            if(timeArr.size() == 0){
                binding.addOrderBtn.setEnabled(false);
            }
        });

        return binding.getRoot();
    }

}
