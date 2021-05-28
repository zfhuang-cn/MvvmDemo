package com.ant.core.loadsir;

import android.content.Context;
import android.view.View;

import com.ant.core.R;
import com.kingja.loadsir.callback.Callback;

public class LoadingCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.view_loading;
    }

    @Override
    protected boolean onReloadEvent(Context context, View view) {
        return true;
    }
}