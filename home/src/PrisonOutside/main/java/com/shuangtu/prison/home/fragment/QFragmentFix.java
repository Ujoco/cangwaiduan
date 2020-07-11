package com.shuangtu.prison.home.fragment;

import java.util.Arrays;
import java.util.List;

public class QFragmentFix extends QFragmentCommon {
    @Override
    public String[] getTitles() {
        String[] str = {"回监确认", "出监确认"};
        return str;
    }

    @Override
    public String[] fragmentsTitle() {
        String[] str = {"com.shuangtu.prison.home.qfragment.fix.QFragmentInfoFix", "com.shuangtu.prison.home.qfragment.fix.QFragmentInfoFix2"};
        return str;
    }

    private final static List<String> tags = Arrays.asList("com.shuangtu.prison.home.qfragment.fix.QFragmentInfoFix", "com.shuangtu.prison.home.qfragment.fix.QFragmentInfoFix2");

    @Override
    public List<String> tags() {
        return tags;
    }
}
