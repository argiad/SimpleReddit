package com.crtmg.simplereddit.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.crtmg.simplereddit.R;
import com.crtmg.simplereddit.interfaces.MainActivityInterface;
import com.crtmg.simplereddit.interfaces.NetworkCallback;
import com.crtmg.simplereddit.model.adapter.PostsListAdapter;
import com.crtmg.simplereddit.model.reddit.Children;
import com.crtmg.simplereddit.model.util.RecyclerTouchListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by argi on 12/27/17.
 */

public class PostsListFragment extends BaseFragment<MainActivityInterface> implements NetworkCallback {

    @BindView(R.id.rvPosts)
    RecyclerView rvPosts;

    @BindView(R.id.reddits)
    Spinner reddits;

    @BindView(R.id.filter)
    Spinner filter;

    private PostsListAdapter adapter;
    private LinearLayoutManager layoutManager;

    private boolean isLoading;
    private int visibleThreshold = 2;

    private RecyclerTouchListener.OnTouchActionListener itemTouchListener = new RecyclerTouchListener.OnTouchActionListener() {
        @Override
        public void onLeftSwipe(View view, int position) {
        }

        @Override
        public void onRightSwipe(View view, int position) {

        }

        @Override
        public void onClick(View view, int position) {
            mListener.showPost(adapter.getItemOnPosition(position));
        }
    };

    public static PostsListFragment getInstance() {
        return new PostsListFragment();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_posts_list;
    }

    @Override
    protected void initWidgets(View fragmentView) {
        ButterKnife.bind(this, fragmentView);

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        adapter = PostsListAdapter.getInstance();
        rvPosts.setAdapter(adapter);
        rvPosts.setLayoutManager(layoutManager);

        // on scroll listener for updating list
        rvPosts.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = layoutManager.getItemCount();
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    mListener.needMorePosts(adapter.getLastName());
                    isLoading = true;
                }
            }
        });

        rvPosts.addOnItemTouchListener(new RecyclerTouchListener(getContext(), rvPosts, itemTouchListener));
        mListener.needMorePosts(null);
    }

    @OnClick(R.id.btnGo)
    public void onClick() {
        isLoading = true;
        adapter.clearList();
        mListener.newRoutes(reddits.getSelectedItem().toString(), filter.getSelectedItem().toString());
    }

    @Override
    public void newPosts(List<Children> posts) {
        adapter.newPosts(posts);
        isLoading = false;
    }

    @Override
    public void returnError(String textToShow) {
        isLoading = false;
        Toast.makeText(getContext(), textToShow, Toast.LENGTH_LONG).show();
    }
}
