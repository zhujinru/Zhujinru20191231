package com.bawei.zhujinru.model;

import com.bawei.zhujinru.contract.HomeContract;
import com.bawei.zhujinru.model.bean.GsonBean;
import com.bawei.zhujinru.util.NetUtil;
import com.google.gson.Gson;

/**
 * 作:a zhujinru
 * 时间:2019/12/31 10:13
 * 作用:M层
 */
public class HomeModel implements HomeContract.IModel {
    //解析数据
    @Override
    public void getHomeData(IModelCallBack iModelCallBack) {
        NetUtil.getInstance().getJsonGet("http://blog.zhaoliang5156.cn/api/news/ranking.json", new NetUtil.MyCallBack() {
            @Override
            public void onGetJson(String json) {
                GsonBean gsonBean = new Gson().fromJson(json, GsonBean.class);
                iModelCallBack.onHomeSuccess(gsonBean);
            }

            @Override
            public void onError(Throwable throwable) {
                iModelCallBack.onHomeFailure(throwable);
            }
        });
    }
}
