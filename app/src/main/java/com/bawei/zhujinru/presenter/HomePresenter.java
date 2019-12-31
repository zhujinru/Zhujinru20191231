package com.bawei.zhujinru.presenter;

import com.bawei.zhujinru.base.BasePresenter;
import com.bawei.zhujinru.contract.HomeContract;
import com.bawei.zhujinru.model.HomeModel;
import com.bawei.zhujinru.model.bean.GsonBean;

/**
 * 作:a zhujinru
 * 时间:2019/12/31 10:13
 * 作用:P层
 */
public class HomePresenter extends BasePresenter<HomeContract.IView> implements HomeContract.IPresenter {

    private HomeModel homeModel;

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
    }

    @Override
    public void getHomeData() {
       homeModel.getHomeData(new HomeContract.IModel.IModelCallBack() {
           @Override
           public void onHomeSuccess(GsonBean gsonBean) {
               view.onHomeSuccess(gsonBean);
           }

           @Override
           public void onHomeFailure(Throwable throwable) {
               view.onHomeFailure(throwable);
           }
       });
    }
}
