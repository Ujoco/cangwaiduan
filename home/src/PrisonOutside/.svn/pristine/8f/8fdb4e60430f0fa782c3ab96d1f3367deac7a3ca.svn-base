package com.shuangtu.prison.home.fragment;

import com.shuangtu.prison.common.q.QFragment;
import com.shuangtu.prison.home.model.ModelMainTitle;
import com.shuangtu.prison.home.qfragment.Discipline.QFragmentViolations;

public class QFragmentDiscipline extends  QFragmentCommons {
    private  int num;


    @Override
    public String[] getTitles() {
      //  String str [] ={"违规录入","点名管理","监室评分","违规监室","电源控制","多媒体控制"};
//        if(num==0){
//            String str [] ={"违规录入"};
//            return str;
//        }else if(num==1){
//            String str [] ={"违规录入"};
//            return str;
//        }
        String str [] ={"违规录入"};
        return str;
    }


    //这是对应的fragment的类名你有多少个 就返回多少个  跟标题对应
    @Override
    public String[] fragmentsTitle() {
        String[] str = {"com.shuangtu.prison.home.qfragment.Discipline.QFragmentViolations"
//                ,"com.shuangtu.prison.home.qfragment.Discipline.QFragmentCallmanagement",
//                "com.shuangtu.prison.home.qfragment.Discipline.QFragmenrScore","com.shuangtu.prison.home.qfragment.Discipline.QFragmentViolationsRoom",
//                "com.shuangtu.prison.home.qfragment.Discipline.QFragmentPowercontrol","com.shuangtu.prison.home.qfragment.Discipline.QFragmentMultimedia",
        };



        return str;


    }

    @Override
    public QFragment getQFragment() {
        return new QFragmentViolations();
    }

    @Override
    public int getIndex() {
        return 0;
    }


}
