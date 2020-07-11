package com.shuangtu.prison.home.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuangtu.prison.home.R;
import com.shuangtu.prison.home.model.ModelClassify;

import java.util.List;

public class AdapterClassify extends BaseQuickAdapter<ModelClassify, BaseViewHolder> {

    public AdapterClassify(int layoutResId, @Nullable List<ModelClassify> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ModelClassify item) {
        helper.setImageResource(R.id.ivCover, item.getRes());
    }
}
