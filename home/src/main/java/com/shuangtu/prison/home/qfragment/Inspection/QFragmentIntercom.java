package com.shuangtu.prison.home.qfragment.Inspection;

import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.shuangtu.prison.common.q.QFragment;
import com.shuangtu.prison.home.R;
//对讲
public class QFragmentIntercom extends QFragment {
    private boolean isSelected;
    private ImageView img_police_office;
    private ImageView img_police_office_select_b;
    private ImageView img_police_office_select;
    private ImageView img_police_office_b;
    private ImageView img_command;
    private ImageView img_command_select;
    private ImageView img_call;
    private FrameLayout frame;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_intercom;
    }

    @Override
    public void initView() {

        img_police_office = root.findViewById(R.id.img_police_office);
        img_police_office_select = root.findViewById(R.id.img_police_office_select);

        img_police_office_b = root.findViewById(R.id.img_police_office_b);
        img_police_office_select_b = root.findViewById(R.id.img_police_office_select_b);

        img_command = root.findViewById(R.id.img_command);
        img_command_select = root.findViewById(R.id.img_command_select);

        img_call = root.findViewById(R.id.img_call);


        //切换fragment
        frame = root.findViewById(R.id.frame);

    }

    @Override
    public void initData() {

        if(img_police_office!=null&&img_police_office_select!=null&&img_police_office_b!=null&img_police_office_select_b!=null&&img_command!=null
        &&img_command_select!=null){
            img_police_office.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(img_police_office_select.getVisibility()==View.GONE){
                        img_police_office_select.setVisibility(View.VISIBLE);
                        img_police_office_select_b.setVisibility(View.GONE);
                        img_command_select.setVisibility(View.GONE);
                    }
                }
            });

            img_police_office_b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(img_police_office_select_b.getVisibility()==View.GONE){
                        img_police_office_select_b.setVisibility(View.VISIBLE);
                        img_police_office_select.setVisibility(View.GONE);
                        img_command_select.setVisibility(View.GONE);
                    }
                }
            });


            img_command.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(img_command_select.getVisibility()==View.GONE){
                        img_police_office_select_b.setVisibility(View.GONE);
                        img_police_office_select.setVisibility(View.GONE);
                        img_command_select.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        if(img_call!=null){
            img_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.frame,new Callinvitation(),"Callinvitation");
                    ft.commit();

                    //ggetFragmentManager
            //    root.getContext().get
//                    FragmentManager fm = getSupportFragmentManager();
//                    FragmentTransaction ft = fm.beginTransaction();
//                    ft.hide(FirstFragment.getInstance())
//.show(SecondFragment.getInstance())
//.commit();


                }
            });
        }
    }

    @Override
    public void initListener() {

    }

    @Override
    public void networkMessage() {

    }
}
