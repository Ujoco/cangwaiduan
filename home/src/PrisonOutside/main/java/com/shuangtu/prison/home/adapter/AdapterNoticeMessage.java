package com.shuangtu.prison.home.adapter;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuangtu.prison.home.R;
import com.shuangtu.prison.home.model.ModelNoticeRecords;

import java.util.List;

public class AdapterNoticeMessage extends BaseSectionQuickAdapter<ModelNoticeRecords, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public AdapterNoticeMessage(int layoutResId, int sectionHeadResId, List<ModelNoticeRecords> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, ModelNoticeRecords item) {
        helper.setText(R.id.tvTitle, item.getHeaderTitle());
        LogUtils.d("适配器里的数据标题"+item.getHeaderTitle());
        helper.setText(R.id.tvContent, "("+item.getNum()+"条)");
        LogUtils.d("适配器里的数据条目"+item.getNum());

    }

    @Override
    protected void convert(BaseViewHolder helper, ModelNoticeRecords item) {
        helper.setText(R.id.tvContent, item.t.getTitle());
      //  LogUtils.d("适配器里的数据标题------------"+item.t.getTitle());
        LogUtils.d("适配器里的数据标题------------"+item.t.getTitle());
    }
}
