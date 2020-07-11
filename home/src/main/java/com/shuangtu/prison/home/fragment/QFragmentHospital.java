package com.shuangtu.prison.home.fragment;

public class QFragmentHospital extends QFragmentCommon {

    public QFragmentHospital(int clickIndex) {
        super(clickIndex);
    }

    @Override
    public String[] getTitles() {
        String[] str = {"用药查询", "收药确认", "预约医生", "预约查询"};
        return str;
    }

    @Override
    public String[] fragmentsTitle() {
        String[] str = {"com.shuangtu.prison.home.qfragment.hospital." +
                "", "com.shuangtu.prison.home.qfragment.hospital.QFragmentHospitalConfirm",
                "com.shuangtu.prison.home.qfragment.hospital.QFragmentHospitalOrder", "com.shuangtu.prison.home.qfragment.hospital.QFragmentHospitalRecord"};
        return str;
    }

    @Override
    public boolean isBack() {
        return fragments[index].isBack();
    }
}
