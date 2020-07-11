package com.shuangtu.prison.home.fragment;

public class QFragmentRollcall extends QFragmentCommon {
    public QFragmentRollcall(int clickIndex) {
        super(clickIndex);
    }

    @Override
    public String[] getTitles() {
        String str []={"点名管理"};

        return str;
    }

    @Override
    public String[] fragmentsTitle() {
        String str []={"com.shuangtu.prison.home.qfragment.Discipline.QFragmentCallmanagement"};

        return str;
    }
}
