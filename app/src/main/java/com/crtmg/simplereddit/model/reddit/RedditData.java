package com.crtmg.simplereddit.model.reddit;

/**
 * Created by argi on 12/27/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RedditData {

    @SerializedName("modhash")
    @Expose
    private String modhash;
    @SerializedName("whitelist_status")
    @Expose
    private String whitelistStatus;
    @SerializedName("children")
    @Expose
    private List<Children> children;
    @SerializedName("after")
    @Expose
    private String after;
    @SerializedName("before")
    @Expose
    private Object before;

    public String getModhash() {
        return modhash;
    }

    public void setModhash(String modhash) {
        this.modhash = modhash;
    }

    public String getWhitelistStatus() {
        return whitelistStatus;
    }

    public void setWhitelistStatus(String whitelistStatus) {
        this.whitelistStatus = whitelistStatus;
    }

    public List<Children> getChildren() {
        return children;
    }

    public void setChildren(List<Children> children) {
        this.children = children;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public Object getBefore() {
        return before;
    }

    public void setBefore(Object before) {
        this.before = before;
    }

}
