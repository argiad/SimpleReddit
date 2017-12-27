package com.crtmg.simplereddit.interfaces;

import com.crtmg.simplereddit.model.reddit.Post;

/**
 * Created by argi on 12/27/17.
 */

public interface MainActivityInterface {
    void needMorePosts(String before);

    void newRoutes(String reddits, String filter);

    void showPost(Post post);
}
