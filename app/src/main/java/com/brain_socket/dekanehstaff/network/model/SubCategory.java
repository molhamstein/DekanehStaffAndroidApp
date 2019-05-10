package com.brain_socket.dekanehstaff.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SubCategory implements Serializable
{

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("priority")
    @Expose
    private String priority;
    @SerializedName("titleAr")
    @Expose
    private String titleAr;
    @SerializedName("titleEn")
    @Expose
    private String titleEn;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("creationDate")
    @Expose
    private String creationDate;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("parentCategoryId")
    @Expose
    private String parentCategoryId;
    private final static long serialVersionUID = 2644600089686502360L;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTitleAr() {
        return titleAr;
    }

    public void setTitleAr(String titleAr) {
        this.titleAr = titleAr;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(String parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

}
