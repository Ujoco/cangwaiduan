package com.shuangtu.prison.home.fragment;

import com.shuangtu.prison.common.q.QFragment;
import com.shuangtu.prison.home.model.ModelMainTitle;
import com.shuangtu.prison.home.qfragment.notice.QFragmentNoticeMessage;

public class QFragmentInfoNotice extends QFragmentNoticeCommon {
    @Override
    public String[] getTitles() {
        String[] str = {"信息播报", "法律法规", "权利义务", "监舍考评", "协查通报"};
        return str;
    }

    @Override
    public String[] getIds() {
        String[] str = {"broadcasting", "laws", "rights", "assessment", "bulletin"};
        return str;
    }

    @Override
    public QFragment fragmentCreate(ModelMainTitle model) {
        return QFragmentNoticeMessage.newInstance(model);
    }

    @Override
    public boolean isBack() {
        return fragments[index].isBack();
    }
}
