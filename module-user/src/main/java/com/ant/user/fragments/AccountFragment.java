package com.ant.user.fragments;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ant.common.router.RouterFragmentPath;
import com.ant.core.mvvm.BaseFragment;
import com.ant.core.model.NormalViewModel;
import com.ant.user.R;
import com.ant.user.databinding.FragmentAccountBinding;


@Route(path = RouterFragmentPath.User.PAGER_USER)
public class AccountFragment extends BaseFragment< NormalViewModel,FragmentAccountBinding> {
    @Override
    protected int layoutId() {
        return R.layout.fragment_account;
    }

    @Override
    protected void init() {

    }
}