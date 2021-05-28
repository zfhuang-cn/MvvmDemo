package com.ant.main.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ant.common.router.RouterActivityPath;
import com.bird.main.R;

/**
 * 应用模块: 主业务模块
 * <p>
 * 类描述: 欢迎页面
 * <p>
 */
public class SplashActivity extends AppCompatActivity {

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_splash);

        mHandler.postDelayed(() -> startToMain(), 3000);
    }

    private void startToMain() {
        ARouter.getInstance().build(RouterActivityPath.Main.PAGER_MAIN).navigation();
    }
}