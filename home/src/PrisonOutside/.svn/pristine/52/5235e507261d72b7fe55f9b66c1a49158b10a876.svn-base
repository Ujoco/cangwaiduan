package com.shuangtu.prison.home.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuangtu.prison.home.R;
import com.shuangtu.prison.home.model.ModelMain;
import com.shuangtu.prison.home.model.ModelMainTitle;

import java.util.List;

import androidx.annotation.Nullable;

public class AdapterMainTitle extends BaseQuickAdapter<ModelMainTitle, BaseViewHolder> {

    public int selectIndex = 0;
    public View beforeSelectView;

    public void clickView(int position, View view) {
        if (beforeSelectView != null) {
            beforeSelectView.setSelected(false);
        }
        selectIndex = position;
        view.setSelected(true);
        beforeSelectView = view;
    }

    public AdapterMainTitle(int layoutResId, @Nullable List<ModelMainTitle> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ModelMainTitle item) {
        helper.setText(R.id.tvTitle, item.getTitle());
        if (selectIndex == helper.getAdapterPosition()){
            clickView(helper.getAdapterPosition(), helper.itemView);
        }
    }
}
