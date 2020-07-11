package com.shuangtu.prison.home.qfragment.Inspection;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.shuangtu.prison.common.q.QFragment;
import com.shuangtu.prison.home.R;
//对讲的二级界面 接听
public class Callinvitation  extends QFragment {

    private ImageView img_answer;
    private FrameLayout frame;

    @Override
    public int getLayoutRes() {
        return R.layout.callinvitation;
    }

    @Override
    public void initView() {
        img_answer = root.findViewById(R.id.img_answer);

        //切换fragment
        frame = root.findViewById(R.id.frame);
    }

    @Override
    public void initData() {
        img_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.frame,new QFragmentPhone(),"Callinvitation");
                ft.commit();
            }
        });
    }

    @Override
    public void initListener() {

    }

    @Override
    public void networkMessage() {

    }
}
