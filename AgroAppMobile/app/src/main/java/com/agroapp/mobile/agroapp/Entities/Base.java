package com.agroapp.mobile.agroapp.Entities;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Base {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("status")
    @Expose
    private short status;

    @SerializedName("createdBy")
    @Expose
    private int createdBy;

    @SerializedName("createdDate")
    @Expose
    private String createdDate;

    @SerializedName("updatedBy")
    @Expose
    private int updatedBy;

    @SerializedName("updatedDate")
    @Expose
    private String updatedDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }
}
