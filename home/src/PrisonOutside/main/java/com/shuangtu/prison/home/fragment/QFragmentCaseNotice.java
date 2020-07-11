package com.shuangtu.prison.home.fragment;

import com.shuangtu.prison.common.q.QFragment;
import com.shuangtu.prison.home.model.ModelMainTitle;
import com.shuangtu.prison.home.qfragment.notice.QFragmentNoticeMeeting;

public class QFragmentCaseNotice extends QFragmentNoticeCommon {
    @Override
    public String[] getTitles() {
        String[] str = {"办案提审", "律师会见通知", "家属会见通知", "检察院会见通知", "法院会见通知"};
        return str;
    }

    @Override
    public String[] getIds() {
        String[] str = {"1", "2", "3", "4", "5"};
        return str;
    }

    @Override
    public QFragment fragmentCreate(ModelMainTitle model) {
        return QFragmentNoticeMeeting.newInstance(model.getId());
    }

    @Override
    public boolean isBack() {
        return fragments[index].isBack();
    }
}
