package com.bawei.zhujinru.base;

/**
 * 作:a zhujinru
 * 时间:2019/12/31 10:11
 * 作用:presenter基类
 */
public abstract class BasePresenter <V> {
    public V view;

    public BasePresenter() {
        initModel();
    }

    public void attach(V view) {
        this.view = view;
    }
    public void detach(){
        view=null;
    }
    protected abstract void initModel();
}
