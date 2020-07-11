package com.shuangtu.prison.home.adapter;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuangtu.prison.common.model.ModelHospitalOrderList;
import com.shuangtu.prison.home.R;

import java.util.List;

public class AdapterHospitalOrderList extends BaseQuickAdapter<ModelHospitalOrderList.RecordsBean, BaseViewHolder> {

    private OnCheckValueChangeListener onCheckValueChangeListener;

    public void setOnCheckValueChangeListener(OnCheckValueChangeListener onCheckValueChangeListener) {
        this.onCheckValueChangeListener = onCheckValueChangeListener;
    }

    public AdapterHospitalOrderList(int layoutResId, @Nullable List<ModelHospitalOrderList.RecordsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ModelHospitalOrderList.RecordsBean item) {
        helper.setText(R.id.tvContent, item.getName());
        CheckBox checkBox = helper.itemView.findViewById(R.id.checkbox);
        checkBox.setChecked(item.isSelect());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setSelect(isChecked);
                if (onCheckValueChangeListener != null){
                    onCheckValueChangeListener.changeValue(AdapterHospitalOrderList.this);
                }
            }
        });
    }

}


