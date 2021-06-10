package com.ant.news.newschannel;

import com.ant.core.model.BaseModel;
import com.ant.core.model.IBaseModelListener;
import com.ant.news.api.ChannelBean;

import java.util.List;

/**
 * 新闻栏目
 */
public class ChannelModel extends BaseModel<List<ChannelBean>, List<ChannelBean>> {

    private static final String CHANNELS = "[{\"code\":\"top\",\"name\":\"头条\"}," +
            "{\"code\":\"guonei\",\"name\":\"国内\"},{\"code\":\"guoji\",\"name\":\"国际\"}," +
            "{\"code\":\"yule\",\"name\":\"娱乐\"},{\"code\":\"tiyu\",\"name\":\"体育\"}," +
            "{\"code\":\"junshi\",\"name\":\"军事\"},{\"code\":\"keji\",\"name\":\"科技\"}," +
            "{\"code\":\"caijing\",\"name\":\"财经\"},{\"code\":\"shishang\",\"name\":\"时尚\"}," +
            "{\"code\":\"youxi\",\"name\":\"游戏\"},{\"code\":\"qiche\",\"name\":\"汽车\"}," +
            "{\"code\":\"jiankang\",\"name\":\"健康\"}]";

    public ChannelModel(IBaseModelListener<List<ChannelBean>> listener) {
        super(false, listener, "news_channel", CHANNELS);
    }

    protected void load() {
    }

    @Override
    public void onDataTransform(List<ChannelBean> data, boolean isFromCache) {
        notifyResultToListener(data, data, isFromCache);
    }
}