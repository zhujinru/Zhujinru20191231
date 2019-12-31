package com.bawei.zhujinru.contract;

import com.bawei.zhujinru.model.bean.GsonBean;

/**
 * 作:a zhujinru
 * 时间:2019/12/31 10:11
 * 作用:契约类
 */
public interface HomeContract {
    //v层
    interface IView{
        void onHomeSuccess(GsonBean gsonBean);
        void onHomeFailure(Throwable throwable);
    }
    //P层
    interface IPresenter{
        void getHomeData();
    }
    //m层
    interface IModel{
        void getHomeData(IModelCallBack iModelCallBack);
        interface IModelCallBack{
            void onHomeSuccess(GsonBean gsonBean);
            void onHomeFailure(Throwable throwable);
        }
    }
}
