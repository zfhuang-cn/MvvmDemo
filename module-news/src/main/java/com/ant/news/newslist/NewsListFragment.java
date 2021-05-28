package com.ant.news.newslist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ant.core.loadsir.EmptyCallback;
import com.ant.core.loadsir.ErrorCallback;
import com.ant.core.loadsir.LoadingCallback;
import com.ant.core.mvvm.BaseFragment;
import com.ant.core.mvvm.ViewStatus;
import com.ant.core.utils.ToastUtil;
import com.ant.news.R;
import com.ant.news.databinding.FragmentNewsListBinding;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.scwang.smart.refresh.header.ClassicsHeader;

/**
 * 新闻列表
 */
public class NewsListFragment extends BaseFragment<FragmentNewsListBinding, NewsListViewModel> implements Observer {
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

        viewModel.dataList.observe(this, newsList -> {
            viewDataBinding.refreshLayout.finishRefresh();
            viewDataBinding.refreshLayout.finishLoadMore();
            mAdapter.setData(newsList);
        });


        viewDataBinding.refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        viewDataBinding.refreshLayout.setOnRefreshListener(refreshLayout -> viewModel.refresh());

        viewDataBinding.refreshLayout.setOnLoadMoreListener(refreshLayout -> viewModel.tryToLoadNextPage());

        viewModel.viewStatusLiveData.observe(this, this);
        mLoadService = LoadSir.getDefault().register(viewDataBinding.refreshLayout,
                (Callback.OnReloadListener) v -> viewModel.refresh());
    }

    @Override
    protected void createViewModel() {
        String channelId = getArguments().getString(BUNDLE_KEY_PARAM_CHANNEL_CODE);
        viewModel = new ViewModelProvider(getActivity(),
                new SavedStateViewModelFactory(getActivity().getApplication(), getActivity(),
                        getArguments()))
                .get(channelId, NewsListViewModel.class);
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
                    ToastUtil.show(getString(R.string.no_more_data));
                    break;
                case REFRESH_ERROR:
                    if (viewModel.dataList.getValue().size() == 0) {
                        mLoadService.showCallback(ErrorCallback.class);
                    } else {
                        ToastUtil.show(viewModel.errorMessage.getValue());
                    }
                    break;
                case LOAD_MORE_FAILED:
                    ToastUtil.show(viewModel.errorMessage.getValue());
                    break;
            }
        }
    }
}