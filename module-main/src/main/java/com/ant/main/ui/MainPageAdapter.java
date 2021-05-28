package com.ant.main.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.alibaba.android.arouter.launcher.ARouter;

import java.util.List;

/**
 * @author zfhuang
 */
public class MainPageAdapter extends FragmentPagerAdapter {
    private List<String> routerFragmentPaths;

    public MainPageAdapter(@NonNull FragmentManager fm) {
        super(fm,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    public void setData(List<String> data) {
        routerFragmentPaths = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (routerFragmentPaths != null && routerFragmentPaths.size() > 0) {
            return  (Fragment)  ARouter.getInstance().build(routerFragmentPaths.get(position)).navigation();
        }
        return null;
    }

    @Override
    public int getCount() {
        return routerFragmentPaths != null ? routerFragmentPaths.size() : 0;
    }
}