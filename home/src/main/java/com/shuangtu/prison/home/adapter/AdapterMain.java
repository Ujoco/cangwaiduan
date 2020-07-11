package com.shuangtu.prison.home.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuangtu.prison.common.net.FishLoadManager;
import com.shuangtu.prison.home.R;
import com.shuangtu.prison.home.model.ModelMain;

import java.util.List;

import androidx.annotation.Nullable;

public class AdapterMain extends BaseQuickAdapter<ModelMain, BaseViewHolder> {

    private int clickSelect = 0;

    public void setClickSelect(int clickSelect) {
        this.clickSelect = clickSelect;
    }

    public AdapterMain(int layoutResId, @Nullable List<ModelMain> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ModelMain item) {
        if (TextUtils.isEmpty(item.getUrl())) {
            helper.setImageResource(R.id.ivBg, item.getRes());
//            helper.setBackgroundRes(R.id.ivBg, item.getRes());
        } else {
            FishLoadManager.loadImage((ImageView) helper.itemView.findViewById(R.id.ivShow), item.getUrl());
            helper.setText(R.id.tvTitle, item.getTitle());
        }

        helper.itemView.setSelected(clickSelect == helper.getLayoutPosition());
    }


}
