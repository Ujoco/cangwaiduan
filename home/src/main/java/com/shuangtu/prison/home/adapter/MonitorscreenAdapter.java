package com.shuangtu.prison.home.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shuangtu.prison.home.R;

import java.util.ArrayList;

public class MonitorscreenAdapter extends RecyclerView.Adapter<MonitorscreenAdapter.Innholder> {
    private ArrayList<String> mlist;
    private View mView;

    @NonNull
    @Override
    public MonitorscreenAdapter.Innholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.monitorscreen_item, parent, false);
        return new Innholder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MonitorscreenAdapter.Innholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void setData(ArrayList<String> mlist) {
        this.mlist=mlist;
        notifyDataSetChanged();
    }

    public class Innholder extends RecyclerView.ViewHolder {
        public Innholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
