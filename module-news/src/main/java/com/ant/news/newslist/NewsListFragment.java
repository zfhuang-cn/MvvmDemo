package com.ant.news.newslist;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ant.core.loadsir.EmptyCallback;
import com.ant.core.loadsir.ErrorCallback;
import com.ant.core.loadsir.LoadingCallback;
import com.ant.core.mvvm.BaseFragment;
import com.ant.core.mvvm.ViewStatus;
import com.ant.news.R;
import com.ant.news.databinding.FragmentNewsListBinding;
import com.blankj.utilcode.util.ToastUtils;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;

/**
 * 新闻列表
 */
public class NewsListFragment extends BaseFragment<NewsListViewModel, FragmentNewsListBinding> implements Observer {
    public static final String BUNDLE_KEY_PARAM_CHANNEL_CODE = "channel_code";
    public static final String BUNDLE_KEY_PARAM_CHANNEL_NAME = "channel_name";

    private LoadService mLoadService;
    private NewsListAdapter mAdapter;

    public static NewsListFragment newInstance(String channelCode, String channelName) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_PARAM_CHANNEL_CODE, channelCode);
        bundle.putString(BUNDLE_KEY_PARAM_CHANNEL_NAME, channelName);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_news_list;
    }

    @Override
    protected void init() {
        mAdapter = new NewsListAdapter();
        viewDataBinding.listView.setHasFixedSize(true);
        viewDataBinding.listView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewDataBinding.listView.setAdapter(mAdapter);

        viewModel.dataList.observe(getViewLifecycleOwner(), list -> {
            viewDataBinding.refreshLayout.finishRefresh();
            viewDataBinding.refreshLayout.finishLoadMore();
            mAdapter.setData(list);
        });

        viewDataBinding.refreshLayout.setOnRefreshListener(refreshLayout -> viewModel.refresh());
        viewDataBinding.refreshLayout.setOnLoadMoreListener(refreshLayout -> viewModel.tryToLoadNextPage());

        viewModel.viewStatusLiveData.observe(this, this);
        mLoadService = LoadSir.getDefault().register(viewDataBinding.refreshLayout,
                (Callback.OnReloadListener) v -> viewModel.refresh());
        viewModel.refresh();
    }

    @Override
    protected String getViewModelKey() {
        return getArguments().getString(BUNDLE_KEY_PARAM_CHANNEL_CODE);
    }

    @Override
    public void onChanged(Object o) {
        if (o instanceof ViewStatus && mLoadService != null) {
            viewDataBinding.refreshLayout.finishRefresh();
            viewDataBinding.refreshLayout.finishLoadMore();
            switch ((ViewStatus) o) {
                case LOADING:
                    mLoadService.showCallback(LoadingCallback.class);
                    break;
                case EMPTY:
                    mLoadService.showCallback(EmptyCallback.class);
                    break;
                case SHOW_CONTENT:
                    mLoadService.showSuccess();
                    break;
                case NO_MORE_DATA:
                    ToastUtils.showLong(R.string.no_more_data);
                    break;
                case REFRESH_ERROR:
                    if (viewModel.dataList.getValue().isEmpty()) {
                        mLoadService.showCallback(ErrorCallback.class);
                    } else {
                        ToastUtils.showLong(viewModel.errorMessage.getValue());
                    }
                    break;
                case LOAD_MORE_FAILED:
                    ToastUtils.showLong(viewModel.errorMessage.getValue());
                    break;
            }
        }
    }
}