package com.crtmg.simplereddit.model.reddit;

/**
 * Created by argi on 12/27/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RedditResponse {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("data")
    @Expose
    private RedditData data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public RedditData getData() {
        return data;
    }

    public void setData(RedditData data) {
        this.data = data;
    }

}