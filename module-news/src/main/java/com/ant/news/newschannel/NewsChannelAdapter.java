package com.ant.news.newschannel;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ant.news.api.ChannelBean;
import com.ant.news.newslist.NewsListFragment;

import java.util.List;

public class NewsChannelAdapter extends FragmentStateAdapter {

    private List<ChannelBean> mChannels;

    public NewsChannelAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public void setChannels(List<ChannelBean> channels) {
        this.mChannels = channels;
        notifyDataSetChanged();
    }

    public CharSequence getPageTitle(int position) {
        return mChannels.get(position).name;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return NewsListFragment.newInstance(mChannels.get(position).code,
                mChannels.get(position).name);
    }

    @Override
    public int getItemCount() {
        return mChannels == null ? 0 : mChannels.size();
    }
}