package com.agroapp.mobile.agroapp.Entities;

import java.util.List;

public class StageResult {

    private int typeResult;
    private Stage result;
    private List<Stage> listResult;

    public int getTypeResult() {
        return typeResult;
    }

    public void setTypeResult(int typeResult) {
        this.typeResult = typeResult;
    }

    public Stage getResult() {
        return result;
    }

    public void setResult(Stage result) {
        this.result = result;
    }

    public List<Stage> getListResult() {
        return listResult;
    }

    public void setListResult(List<Stage> listResult) {
        this.listResult = listResult;
    }
}
