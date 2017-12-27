package com.crtmg.simplereddit.view.fragment;

import android.view.View;
import android.webkit.WebView;

import com.crtmg.simplereddit.R;
import com.crtmg.simplereddit.model.reddit.Post;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by argi on 12/27/17.
 */

public class PostFragment extends BaseFragment {

    @BindView(R.id.webView)
    WebView webView;

    private Post post;

    public static PostFragment getInstance(Post post) {
        PostFragment fragment = new PostFragment();
        fragment.post = post;
        return fragment;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_post;
    }

    @Override
    protected void initWidgets(View fragmentView) {
        ButterKnife.bind(this, fragmentView);

        webView.loadUrl(post.getUrl());
    }


}
