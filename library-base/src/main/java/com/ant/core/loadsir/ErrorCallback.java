package com.ant.core.loadsir;

import com.ant.core.R;
import com.kingja.loadsir.callback.Callback;

public class ErrorCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.view_error;
    }
}