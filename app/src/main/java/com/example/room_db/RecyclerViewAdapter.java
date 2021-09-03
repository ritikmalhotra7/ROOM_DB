package com.example.room_db;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room_db.databinding.RecycylerViewItemsBinding;

public class RecyclerViewAdapter extends ListAdapter<App,RecyclerViewAdapter.ViewHolder> {
    AppViewModel vm;
    ClickListeners dc;

    protected RecyclerViewAdapter(@NonNull DiffUtil.ItemCallback<App> diffCallback, ClickListeners delete) {
        super(diffCallback);
        this.dc = delete;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RecycylerViewItemsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        App current = getItem(position);
        holder.b.textView.setText(current.getName());
        holder.b.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dc.clicked(current.getId());
            }
        });
    }
    static class AppDiff extends DiffUtil.ItemCallback<App> {
        @Override
        public boolean areItemsTheSame(@NonNull App oldItem, @NonNull App newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull App oldItem, @NonNull App newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecycylerViewItemsBinding b;
        public ViewHolder(RecycylerViewItemsBinding binding) {
            super(binding.getRoot());
            this.b = binding;
        }
    }
}
