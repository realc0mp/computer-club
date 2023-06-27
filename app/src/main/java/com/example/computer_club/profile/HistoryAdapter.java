package com.example.computer_club.profile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.computer_club.databinding.ItemOrderBinding;
import com.example.computer_club.tables.Order;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private ArrayList<Order> items = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOrderBinding binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order item = items.get(position);
        holder.binding.date.setText(item.date);
        holder.binding.time.setText(item.time);
        holder.binding.compName.setText(item.compName);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItems(List<Order> items){
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ItemOrderBinding binding;

        public ViewHolder(@NonNull View itemView, ItemOrderBinding binding) {
            super(itemView);
            this.binding = binding;
        }
    }
}
