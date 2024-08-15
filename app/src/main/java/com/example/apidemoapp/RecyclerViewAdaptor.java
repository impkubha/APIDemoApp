package com.example.apidemoapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Activity context;
    ArrayList<DataModel> data;
    public RecyclerViewAdapter(Activity context,ArrayList<DataModel> data){
        this.data=data;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View listItem= layoutInflater.inflate(R.layout.list_items,parent,false);
        ViewHolder viewHolder= new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataModel current=data.get(position);
        holder.txtName.setText(current.getName());
        holder.txtAddress.setText(current.getAddress());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtAddress;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName=itemView.findViewById(R.id.txtName);
            txtAddress=itemView.findViewById(R.id.txtAddress);
        }
    }
}
