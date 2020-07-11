package com.shuangtu.prison.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shuangtu.prison.common.model.ModelPolice;
import com.shuangtu.prison.common.net.FishLoadManager;
import com.shuangtu.prison.home.R;

import java.util.List;

public class PoliceinformationAdapter extends RecyclerView.Adapter<PoliceinformationAdapter.InnerHolder> {
    private List<ModelPolice.RecordsBean> mlist;
    private View mView;
    private ImageView mImg_police;

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.police_information, parent, false);
        return new InnerHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
       // FishLoadManager.loadCirclCorner(mlist.get(position), (ImageView) holder.itemView.findViewById(R.id.img_item), 10, 10);
        mImg_police = holder.itemView.findViewById(R.id.img_police);
        FishLoadManager.loadCirclCorner(mlist.get(position).getAvatar(),mImg_police);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void setData(List<ModelPolice.RecordsBean> records) {
        this.mlist = records;
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
