package com.shuangtu.prison.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shuangtu.prison.home.R;

import java.util.ArrayList;

public class CallmanagementAdapter extends RecyclerView.Adapter<CallmanagementAdapter.Innholder> {

    private View mView;
    private ArrayList<String> mlist;

    @NonNull
    @Override
    public Innholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.callmanagement_item, parent, false);
        return new Innholder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull Innholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void setdata(ArrayList<String> mlist) {
        this.mlist =mlist ;
        notifyDataSetChanged();
    }

    public class Innholder extends RecyclerView.ViewHolder {
        public Innholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
