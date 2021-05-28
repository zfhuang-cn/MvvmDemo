package com.ant.core.mvvm;

import com.ant.core.model.BaseModel;
import com.ant.core.model.PagingResult;

public class NormalViewModel extends BaseViewModel<BaseModel, String> {
    @Override
    public void onLoadFinish(String s, PagingResult... pagingResults) {

    }

    @Override
    public void onLoadFail(String prompt, PagingResult... pagingResults) {

    }
}