package com.ant.main.ui;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ant.common.router.RouterActivityPath;
import com.ant.common.router.RouterFragmentPath;
import com.ant.core.mvvm.BaseActivity;
import com.ant.core.model.NormalViewModel;
import com.ant.main.R;
import com.ant.main.databinding.ActivityMainBinding;
import com.blankj.utilcode.util.ColorUtils;

import java.util.ArrayList;
import java.util.List;

import me.majiajie.pagerbottomtabstrip.NavigationController;

@Route(path = RouterActivityPath.Main.PAGER_MAIN)
public class MainActivity extends BaseActivity<NormalViewModel,ActivityMainBinding> {

    private MainPageAdapter adapter;

    private NavigationController mNavigationController;

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        setSupportActionBar(viewDataBinding.toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.app_name));

        mNavigationController = viewDataBinding.bottomView.material()
                .addItem(R.drawable.tab_bar_home,"首页", ColorUtils.getColor(R.color.main_bottom_check_color))
                .addItem(R.drawable.tab_bar_crux,"地区", ColorUtils.getColor(R.color.main_bottom_check_color))
                .addItem(R.drawable.tab_bar_category_ic,"地区", ColorUtils.getColor(R.color.main_bottom_check_color))
                .addItem(R.drawable.tab_bar_account,"我的", ColorUtils.getColor(R.color.main_bottom_check_color))
                .setDefaultColor(ColorUtils.getColor( R.color.main_bottom_default_color))
                .enableAnimateLayoutChanges()
                .build();
        mNavigationController.setHasMessage(2, true);
        adapter = new MainPageAdapter(getSupportFragmentManager());
        viewDataBinding.container.setOffscreenPageLimit(1);
        viewDataBinding.container.setAdapter(adapter);
        mNavigationController.setupWithViewPager(viewDataBinding.container);

        initFragmentPath();
    }

    private void initFragmentPath() {
        //其他组件提供的fragment路由地址
        List<String> paths = new ArrayList<>();
        paths.add(RouterFragmentPath.Home.PAGER_HOME);
        paths.add(RouterFragmentPath.User.PAGER_USER);
        paths.add(RouterFragmentPath.User.PAGER_USER);
        paths.add(RouterFragmentPath.User.PAGER_USER);
        adapter.setData(paths);
    }

}