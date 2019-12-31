package com.bawei.zhujinru.view.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.zhujinru.R;
import com.bawei.zhujinru.base.BaseActivity;
import com.bawei.zhujinru.base.BasePresenter;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends BaseActivity {


    @BindView(R.id.ewm)
    ImageView ewm;
    @BindView(R.id.wx)
    Button wx;
    @BindView(R.id.qq)
    Button qq;

    @Override
    protected BasePresenter providerPresenter() {
        return null;
    }

    @Override
    protected void initData() {
//生成二维码
        Bitmap image = CodeUtils.createImage("朱金茹", 450, 450, null);
        ewm.setImageBitmap(image);
    }

    @Override
    protected void initView() {
        CodeUtils.init(this);
        //长按二维码吐司
       ewm.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View view) {
               CodeUtils.analyzeByImageView(ewm, new CodeUtils.AnalyzeCallback() {
                   @Override
                   public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                       Toast.makeText(Main2Activity.this, result, Toast.LENGTH_SHORT).show();
                   }

                   @Override
                   public void onAnalyzeFailed() {
                       Toast.makeText(Main2Activity.this, "失败", Toast.LENGTH_SHORT).show();
                   }
               });
               return true;
           }
       });
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main2;
    }

//点击按钮吐司相应内容
    @OnClick({R.id.wx, R.id.qq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wx:
                EventBus.getDefault().post("微信");
                break;
            case R.id.qq:
                EventBus.getDefault().post("QQ");
                break;
        }
    }
//绑定
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
//解绑
    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe
    public void onEvenget(String string){
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }
    @Subscribe
    public void onEvenget2(String string){
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }
}
