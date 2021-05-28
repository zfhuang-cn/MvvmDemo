package com.ant.news.homefragment;

import com.ant.core.model.BaseModel;
import com.ant.core.model.IBaseModelListener;
import com.ant.news.api.ChannelBean;

import java.util.ArrayList;
import java.util.List;

public class ChannelsModel extends BaseModel<List<ChannelBean>> {

    public ChannelsModel(IBaseModelListener<List<ChannelBean>> iBaseModelListener) {
        super(iBaseModelListener);
    }

    @Override
    protected void notifyCacheData(List<ChannelBean> channelBeans) {

    }

    public void load() {
        //新闻栏目
        List<ChannelBean> channels = new ArrayList<>();
        channels.add(new ChannelBean("top", "头条"));
        channels.add(new ChannelBean("guonei", "国内"));
        channels.add(new ChannelBean("guoji", "国际"));
        channels.add(new ChannelBean("yule", "娱乐"));
        channels.add(new ChannelBean("tiyu", "体育"));
        channels.add(new ChannelBean("junshi", "军事"));
        channels.add(new ChannelBean("keji", "科技"));
        channels.add(new ChannelBean("caijing", "财经"));
        channels.add(new ChannelBean("shishang", "时尚"));
        channels.add(new ChannelBean("youxi", "游戏"));
        channels.add(new ChannelBean("qiche", "汽车"));
        channels.add(new ChannelBean("jiankang", "健康"));
        iBaseModelListener.onLoadFinish(channels);
    }
}