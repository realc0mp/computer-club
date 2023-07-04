package com.example.computer_club.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.example.computer_club.databinding.ItemCompBinding;
import com.example.computer_club.tables.Comp;
import com.example.computer_club.tables.Order;

import java.util.ArrayList;
import java.util.List;

public class CompsAdapter extends RecyclerView.Adapter<CompsAdapter.ViewHolder> {

    private ArrayList<Comp> items = new ArrayList<>();
    private DialogBinder binder;

    public CompsAdapter(DialogBinder binder){
        this.binder = binder;
    }

    public void setBinder(DialogBinder binder) {
        this.binder = binder;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCompBinding binding = ItemCompBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(binding.getRoot(), binding, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comp item = items.get(position);

        holder.binding.compImage.setImageDrawable(AppCompatResources.getDrawable(holder.context, item.image));
        holder.binding.compName.setText(item.name);
        holder.binding.compDesc.setText(item.characteristics);

        holder.binding.getRoot().setOnClickListener(v -> {
            binder.bindDialog(item);
        });

        setAnimation(holder.itemView, holder.context);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void refreshItems(List<Comp> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }


    public void removeItem(Comp item){
        int pos = items.indexOf(item);
        this.items.remove(item);
        notifyItemRemoved(pos);
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView, ItemCompBinding binding, Context context) {
            super(itemView);
            this.binding = binding;
            this.context = context;
        }

        ItemCompBinding binding;
        Context context;
    }

    private void setAnimation(View view,  Context context){
        Animation anim = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        view.startAnimation(anim);
    }
}
