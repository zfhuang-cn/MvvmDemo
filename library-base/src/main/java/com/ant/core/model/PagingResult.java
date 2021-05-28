package com.ant.core.model;

public class PagingResult {

    public boolean isEmpty;
    public boolean isFirstPage;
    public boolean hasNextPage;

    public PagingResult(boolean isEmpty, boolean isFirstPage, boolean hasNextPage) {
        this.isEmpty = isEmpty;
        this.isFirstPage = isFirstPage;
        this.hasNextPage = hasNextPage;
    }
}