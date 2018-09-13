package com.agroapp.mobile.agroapp.Entities;

import java.util.List;

public class UserResult {

    private int typeResult;
    private User result;
    private List<User> listResult;

    public int getTypeResult() {
        return typeResult;
    }

    public void setTypeResult(int typeResult) {
        this.typeResult = typeResult;
    }

    public User getResult() {
        return result;
    }

    public void setResult(User result) {
        this.result = result;
    }

    public List<User> getListResult() {
        return listResult;
    }

    public void setListResult(List<User> listResult) {
        this.listResult = listResult;
    }
}
