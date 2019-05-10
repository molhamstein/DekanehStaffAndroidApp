package com.brain_socket.dekanehstaff.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Media implements Serializable
{

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("jpgUrl")
    @Expose
    private Object jpgUrl;
    @SerializedName("id")
    @Expose
    private String id;
    private final static long serialVersionUID = 3771224359792452857L;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Object getJpgUrl() {
        return jpgUrl;
    }

    public void setJpgUrl(Object jpgUrl) {
        this.jpgUrl = jpgUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
