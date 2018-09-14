package com.agroapp.mobile.agroapp.Entities;

import java.util.List;

public class Result<T> {
    private int typeResult;
    private T result;
    private List<T> listResult;

    public int getTypeResult() {
        return typeResult;
    }

    public void setTypeResult(int typeResult) {
        this.typeResult = typeResult;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public List<T> getListResult() {
        return listResult;
    }

    public void setListResult(List<T> listResult) {
        this.listResult = listResult;
    }
}
