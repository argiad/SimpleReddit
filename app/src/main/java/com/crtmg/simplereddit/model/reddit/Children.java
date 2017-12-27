package com.crtmg.simplereddit.model.reddit;

/**
 * Created by argi on 12/27/17.
 */

public class Children {

    private String kind;
    private Post data;

    public String getKind() {
        return kind;
    }

    public Post getData() {
        return data;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setData(Post data) {
        this.data = data;
    }
}
