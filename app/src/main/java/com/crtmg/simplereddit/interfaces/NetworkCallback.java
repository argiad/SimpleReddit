package com.crtmg.simplereddit.interfaces;

import com.crtmg.simplereddit.model.reddit.Children;

import java.util.List;

/**
 * Created by argi on 12/27/17.
 */

public interface NetworkCallback {
    void newPosts(List<Children> posts);

    void returnError(String textToShow);
}
