package com.haha.mchis;


import android.os.Bundle;

import com.haha.mchis.base.BaseActivity;

public class LoginActivity extends BaseActivity {
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected Boolean getIsFullScreen() {
        return true;
    }

    @Override
    protected Boolean getIsShowTitle() {
        return false;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }
}
