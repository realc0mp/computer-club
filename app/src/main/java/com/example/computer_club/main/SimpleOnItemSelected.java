package com.example.computer_club.main;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

public interface SimpleOnItemSelected extends AdapterView.OnItemSelectedListener {

    @Override
    void onItemSelected(AdapterView<?> parent, View view, int position, long id);

    @Override
    default void onNothingSelected(AdapterView<?> parent){}
}
