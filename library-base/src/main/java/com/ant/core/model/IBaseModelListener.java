package com.ant.core.model;

public interface IBaseModelListener<DATA> {
    void onLoadFinish(DATA data, PagingResult... pagingResults);

    void onLoadFail(String prompt, PagingResult... pagingResults);
}
