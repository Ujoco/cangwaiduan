package com.shuangtu.prison.home.adapter;

import android.graphics.Color;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuangtu.prison.common.constant.Global;
import com.shuangtu.prison.common.model.ModelGoodsClassify;
import com.shuangtu.prison.home.R;

import java.util.List;

public class AdapterGoodsListClassify extends BaseQuickAdapter<ModelGoodsClassify, BaseViewHolder> {

    private int selectIndex = 0;

    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
    }

    public int getSelectIndex() {
        return selectIndex;
    }

    public AdapterGoodsListClassify(int layoutResId, @Nullable List<ModelGoodsClassify> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ModelGoodsClassify item) {
        helper.setText(R.id.tvTitle, item.getText());

        Button textView = (Button) helper.itemView;
        if (selectIndex == helper.getAdapterPosition()){
            helper.itemView.setSelected(true);
            textView.setTextColor(Color.WHITE);
        }else {
            helper.itemView.setSelected(false);
            textView.setTextColor(Global.toColorFromString("#1F417C"));
        }
    }
}
