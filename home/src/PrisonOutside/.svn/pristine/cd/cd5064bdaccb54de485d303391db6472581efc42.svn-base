package com.shuangtu.prison.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.shuangtu.prison.home.R;

import java.util.ArrayList;

public class SaveAdapter extends RecyclerView.Adapter<SaveAdapter.Innholder> {

    private View mView;
    private ArrayList<String> mlist;

    @NonNull
    @Override
    public SaveAdapter.Innholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.save_item, parent, false);
        return new Innholder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final SaveAdapter.Innholder holder, final int position) {
        if(holder!=null) {

            holder.mImg_select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtils.d("这里有没有调用。。。。");
//                if(!item.isSelected()&&holder.mImg_isSelected!=null& holder.mImg_isSelected.getVisibility()==View.GONE){
//                    holder.mImg_isSelected.setVisibility(View.VISIBLE);
//                    item.isSelected=true;
//                    holder.itemView.setBackgroundColor(Color.parseColor("#1C6596"));
//
//
//                }else{
//                    holder.itemView.setBackgroundColor(Color.parseColor("#0F254D"));
//                    holder.mImg_isSelected.setVisibility(View.GONE);
//                    item.isSelected=false;
//
//                }


                    LogUtils.d("我点击了" + position);
                    if (holder.mImg_hook != null && holder.mImg_hook.getVisibility() == View.GONE) {
                        holder.mImg_hook.setVisibility(View.VISIBLE);
                    } else if (holder.mImg_hook.getVisibility() == View.VISIBLE) {
                        holder.mImg_hook.setVisibility(View.GONE);
                    }


                }

            });

        }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void setData(ArrayList<String> list) {
        this.mlist= list;
        notifyDataSetChanged();
    }


    public static interface OnItemClickListenenr {
        void onItemClick(int position);

    }
    private SaveAdapter.OnItemClickListenenr getListener;



    public void setOnItemClickListener(SaveAdapter.OnItemClickListenenr listener) {
        this.getListener = listener;
    }



    public class Innholder extends RecyclerView.ViewHolder {
        private ImageView mImg_select;
        private  ImageView mImg_hook;
        public Innholder(@NonNull View itemView) {
            super(itemView);
            //选择框
            mImg_select = itemView.findViewById(R.id.img_select);

            //勾
            mImg_hook = itemView.findViewById(R.id.img_hook);

        }
    }
}
