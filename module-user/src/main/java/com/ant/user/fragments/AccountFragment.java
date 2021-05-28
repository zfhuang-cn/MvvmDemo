package com.ant.user.fragments;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ant.common.router.RouterFragmentPath;
import com.ant.core.mvvm.BaseFragment;
import com.ant.core.mvvm.NormalViewModel;
import com.bird.user.R;
import com.bird.user.databinding.FragmentAccountBinding;

@Route(path = RouterFragmentPath.User.PAGER_USER)
public class AccountFragment extends BaseFragment<FragmentAccountBinding, NormalViewModel> {
    @Override
    protected int layoutId() {
        return R.layout.fragment_account;
    }

    @Override
    protected void init() {

    }
}