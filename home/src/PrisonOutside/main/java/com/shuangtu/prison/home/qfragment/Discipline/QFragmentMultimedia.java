package com.shuangtu.prison.home.qfragment.Discipline;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.os.Bundle;

        import com.shuangtu.prison.common.q.QFragment;
        import com.shuangtu.prison.home.R;
        import com.shuangtu.prison.home.adapter.SaveAdapter;
        import com.shuangtu.prison.home.adapter.EntertainmentAdapter;

        import java.util.ArrayList;


/// 多媒体控制
public class QFragmentMultimedia extends QFragment {

    private RecyclerView mRv_entertainment;

    //娱乐节目的模拟数据测试
    private  ArrayList<String> alist = new ArrayList<>();
    //自救视频的模拟数据
    private  ArrayList<String> list = new ArrayList<>();

    private EntertainmentAdapter mEntertainmentAdapter;
    private RecyclerView mRv_save;
    private SaveAdapter mSaveAdapter;





    @Override
    public int getLayoutRes() {
        return R.layout.activity_qfragment_multimedia;
    }

    @Override
    public void initView() {

        for(int i =0 ;i<20;i++){
            alist.add("AAAAAA");
            list.add("SSSSS");
        }



        mRv_entertainment =root. findViewById(R.id.rv_entertainment);

        LinearLayoutManager SetLayoutManager= (new LinearLayoutManager(root.getContext()));
        SetLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv_entertainment.setLayoutManager(SetLayoutManager);
        //    mLargeAdapter = new LargeAdapter(viewRoot.getContext(),largelist);
        mEntertainmentAdapter = new EntertainmentAdapter();
        mEntertainmentAdapter.setData(alist);
        mRv_entertainment.setAdapter(mEntertainmentAdapter);


        mRv_save = root.findViewById(R.id.rv_save);
        LinearLayoutManager saveetLayoutManager= (new LinearLayoutManager(root.getContext()));
        saveetLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv_save.setLayoutManager(saveetLayoutManager);
        //    mLargeAdapter = new LargeAdapter(viewRoot.getContext(),largelist);
        mSaveAdapter = new SaveAdapter();
        mSaveAdapter.setData(list);
        mRv_save.setAdapter(mSaveAdapter);

    }

    @Override
    public void initData() {
                mEntertainmentAdapter.setOnItemClickListener(new EntertainmentAdapter.OnItemClickListenenr() {
            @Override
            public void onItemClick(int position) {

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
