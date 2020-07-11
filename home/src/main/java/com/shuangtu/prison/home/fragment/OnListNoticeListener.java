package com.shuangtu.prison.home.fragment;

import com.shuangtu.prison.home.qfragment.notice.QFragmentNoticeList;

public interface OnListNoticeListener {

    void messageData(QFragmentNoticeList fragment, int pageNo, int pageSize, String dateStart, String dateEnd, String id, String title);
}
