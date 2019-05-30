package com.haha.mchis.base;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.haha.mchis.R;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    /***设置布局名称R.layout.xxxxx*/
    protected abstract int getContentViewLayoutID();

    /***设置是否全屏*/
    protected abstract Boolean getIsFullScreen();

    /***设置是否需要标题*/
    protected abstract Boolean getIsShowTitle();

    /*** 初始化界面*/
    protected abstract void initView(Bundle savedInstanceState);

    /*** 初始化数据*/
    protected abstract void initData();

    /*** 绑定事件*/
    protected abstract void initEvent();

    /*** 窗口全屏*/
    private void setFullScreen(Boolean fullScreen) {
        if (fullScreen) {
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                requestWindowFeature(Window.FEATURE_NO_TITLE);
            } else {
                if (getSupportActionBar() != null) {
                    getSupportActionBar().hide();
                }
            }

        }
    }

    /*** 窗口标题*/
    private void setShowTitle(Boolean showTitle) {
        if (!showTitle) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
    }

    protected int setStatusBarColor() {
        return R.color.colorPrimary;
    }

    /*** 是否沉浸状态栏*/
    protected boolean GetTranslucentStatusBar() {
        return true;
    }

    /***沉浸状态栏（4.4以上系统有效） 设置状态栏颜色*/
    protected void initSystemBarTint() {
        Window window = getWindow();
        if (GetTranslucentStatusBar()) {
            /** 设置状态栏全透明*/
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            return;
        }
        /** 沉浸式状态栏*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            /**5.0以上使用原生方法*/
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(setStatusBarColor());
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            /**4.4-5.0使用三方工具类，有些4.4的手机有问题，这里为演示方便，不使用沉浸式*/
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(setStatusBarColor());
        }
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setShowTitle(getIsShowTitle());
        initSystemBarTint();
        if (getContentViewLayoutID() != 0) {
            setContentView(getContentViewLayoutID());
            setFullScreen(getIsFullScreen());
            initView(savedInstanceState);
        }
        initEvent();
        initData();
    }

}
