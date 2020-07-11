package com.shuangtu.prison.home.qfragment.Inspection;

import android.widget.FrameLayout;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.shuangtu.prison.common.q.QFragment;
import com.shuangtu.prison.home.R;

public class QFragmentIntercoms extends QFragment {
    @Override
    public int getLayoutRes() {
        return R.layout.callinvitations;
    }

    @Override
    public void initView() {
        //切换fragment
        FrameLayout frame = root.findViewById(R.id.frame);
    }

    @Override
    public void initData() {



        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.frame,new QFragmentIntercom(),"QFragmentIntercom");
        ft.commit();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void networkMessage() {

    }
}
