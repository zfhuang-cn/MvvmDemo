package com.ant.news.newschannel;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ant.common.router.RouterFragmentPath;
import com.ant.core.mvvm.BaseFragment;
import com.ant.news.R;
import com.ant.news.databinding.FragmentNewsHomeBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * 新闻栏目
 */
@Route(path = RouterFragmentPath.Home.PAGER_HOME)
public class NewsChannelFragment extends BaseFragment<NewsChannelViewModel,FragmentNewsHomeBinding> {

    private NewsChannelAdapter adapter;

    @Override
    protected int layoutId() {
        return R.layout.fragment_news_home;
    }

    @Override
    protected void init() {
        adapter = new NewsChannelAdapter(this);
        viewDataBinding.tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewDataBinding.viewPager.setAdapter(adapter);

        new TabLayoutMediator(viewDataBinding.tabLayout, viewDataBinding.viewPager, true,
                (tab, position) -> tab.setText(adapter.getPageTitle(position)))
                .attach();

        viewDataBinding.viewPager.setOffscreenPageLimit(1);
        viewModel.dataList.observe(this, channels -> adapter.setChannels(channels));
    }
}