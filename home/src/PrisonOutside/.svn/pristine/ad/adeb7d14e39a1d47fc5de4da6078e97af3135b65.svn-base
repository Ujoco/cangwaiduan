package com.shuangtu.prison.home.adapter;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.shuangtu.prison.home.R;
import com.shuangtu.prison.home.socket.model.ModelOvertime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MonitorAdapter extends RecyclerView.Adapter<MonitorAdapter.Innholder>{

    private View mView;
    private ArrayList<String> mlist;
    private TextView mTv_time;
    private SimpleDateFormat mSimpleDateFormat;
    private Date mDate;

    @NonNull
    @Override
    public MonitorAdapter.Innholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.monitor_itme, parent, false);
        return new Innholder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MonitorAdapter.Innholder holder, int position) {
        mTv_time = holder.itemView.findViewById(R.id.tv_time);



    }



    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void setdata(ArrayList<String> mlist) {
        this.mlist = mlist;
        notifyDataSetChanged();
    }

    public class Innholder extends RecyclerView.ViewHolder {
        public Innholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
