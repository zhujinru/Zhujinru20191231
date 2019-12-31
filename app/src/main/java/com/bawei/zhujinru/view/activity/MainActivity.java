package com.bawei.zhujinru.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.zhujinru.R;
import com.bawei.zhujinru.base.BaseActivity;
import com.bawei.zhujinru.contract.HomeContract;
import com.bawei.zhujinru.model.bean.GsonBean;
import com.bawei.zhujinru.presenter.HomePresenter;
import com.bawei.zhujinru.view.adapter.MyAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作:a zhujinru
 * 时间:2019/12/31 10:15
 * 作用:主页面
 */
public class MainActivity extends BaseActivity<HomePresenter> implements HomeContract.IView {

    @BindView(R.id.acheck)
    TextView acheck;
    @BindView(R.id.arecycler)
    RecyclerView arecycler;

    @Override
    protected HomePresenter providerPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initData() {
        mPresenter.getHomeData();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }
//列表展示
    @Override
    public void onHomeSuccess(GsonBean gsonBean) {
        List<GsonBean.RankingBean> ranking = gsonBean.getRanking();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        arecycler.setLayoutManager(linearLayoutManager);
        MyAdapter myAdapter = new MyAdapter(ranking);
        arecycler.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new MyAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int i) {
                GsonBean.RankingBean rankingBean = ranking.get(i);
                Toast.makeText(MainActivity.this, rankingBean.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onHomeFailure(Throwable throwable) {
        Log.e("xxx",throwable.getMessage());
    }

//跳转页面
    @OnClick(R.id.acheck)
    public void onViewClicked() {
        startActivity(new Intent(MainActivity.this,Main2Activity.class));
    }
}
