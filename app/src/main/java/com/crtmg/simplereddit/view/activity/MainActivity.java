package com.crtmg.simplereddit.view.activity;

import android.os.Bundle;

import com.crtmg.simplereddit.R;
import com.crtmg.simplereddit.interfaces.MainActivityInterface;
import com.crtmg.simplereddit.model.BuisnessLogic;
import com.crtmg.simplereddit.model.SimpleReddit;
import com.crtmg.simplereddit.model.reddit.Post;
import com.crtmg.simplereddit.view.fragment.BaseFragment;
import com.crtmg.simplereddit.view.fragment.PostFragment;
import com.crtmg.simplereddit.view.fragment.PostsListFragment;

/**
 * Created by argi on 12/27/17.
 */

public class MainActivity extends BaseActivity implements MainActivityInterface {

    PostsListFragment postsListFragment;
    BuisnessLogic buisenssLogic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        buisenssLogic = ((SimpleReddit) getApplicationContext()).buisnessLogic;
        postsListFragment = PostsListFragment.getInstance();
        showFragment(postsListFragment);

    }

    @Override
    public void needMorePosts(String before) {
        buisenssLogic.needMore(before, postsListFragment);
    }

    @Override
    public void newRoutes(String reddits, String filter) {
        buisenssLogic.setNewRoute(reddits, filter);
        buisenssLogic.needMore(null, postsListFragment);
    }

    @Override
    public void showPost(Post post) {
        showFragment(PostFragment.getInstance(post));
    }

    @Override
    public void onBackPressed() {
        if (postsListFragment.isVisible())
            super.onBackPressed();
        showFragment(postsListFragment);
    }

    private void showFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commitNow();

    }
}
