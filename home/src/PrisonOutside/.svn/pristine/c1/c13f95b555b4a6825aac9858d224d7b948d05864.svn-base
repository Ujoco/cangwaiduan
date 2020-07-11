package com.shuangtu.prison.home.qfragment.Inspection;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bin.david.form.core.SmartTable;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.shuangtu.prison.common.constant.Global;
import com.shuangtu.prison.common.model.ModelNoticeMessage;
import com.shuangtu.prison.common.net.Api;
import com.shuangtu.prison.common.q.QFragment;
import com.shuangtu.prison.home.R;
import com.shuangtu.prison.home.adapter.AdapterNoticeMessage;
import com.shuangtu.prison.home.fragment.QFragmentWeb;
import com.shuangtu.prison.home.model.ModelMainTitle;
import com.shuangtu.prison.home.model.ModelNoticeRecords;
import com.shuangtu.prison.home.qfragment.notice.QFragmentNoticeList;
import com.shuangtu.prison.home.table.TablePolicenotice;
import com.trello.rxlifecycle3.android.FragmentEvent;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

//警情通知
public class QFragmentPolicenotice extends QFragment {

    private SmartTable table;
    private ArrayList<TablePolicenotice> mlist = new ArrayList<>();
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_policenotice;
    }

    @Override
    public void initView() {
        for(int i  =0 ;i<5;i++){
            mlist.add(new TablePolicenotice("王某某","2019年11月01日","警情演示文字警情演示文字警情演示文字"));

        }
        table = root.findViewById(R.id.table);



    }

    @Override
    public void initData() {


        table.setData(mlist);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void networkMessage() {

    }


}
