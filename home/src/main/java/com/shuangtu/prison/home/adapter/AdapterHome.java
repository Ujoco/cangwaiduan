package com.shuangtu.prison.home.adapter;

import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuangtu.prison.home.R;

import java.util.List;

import androidx.annotation.Nullable;

public class AdapterHome extends BaseQuickAdapter<String , BaseViewHolder> {

    public AdapterHome(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tvTitle, item);
    }
}
