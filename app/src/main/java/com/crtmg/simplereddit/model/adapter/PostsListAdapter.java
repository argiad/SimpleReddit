package com.crtmg.simplereddit.model.adapter;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.crtmg.simplereddit.R;
import com.crtmg.simplereddit.model.reddit.Children;
import com.crtmg.simplereddit.model.reddit.Post;
import com.crtmg.simplereddit.model.util.LoadImage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by argi on 12/27/17.
 */

public class PostsListAdapter extends RecyclerView.Adapter<PostsListAdapter.PostViewHolder> {


    private List<Children> posts = new ArrayList<>();

    private PostsListAdapter() {
    }

    public static PostsListAdapter getInstance() {
        PostsListAdapter adapter = new PostsListAdapter();
        return adapter;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        final Post post = posts.get(position).getData();

        holder.tvName.setText(post.getTitle());
        holder.tvContent.setText(post.getAuthor());
        holder.ivTumbnail.setImageBitmap(null);

        new LoadImage(holder.ivTumbnail ).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, post.getThumbnail());

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void newPosts(List<Children> posts) {
        this.posts.addAll(posts);
        notifyDataSetChanged();
    }

    public Post getItemOnPosition(int position) {
        if (posts != null && posts.size() > position)
            return posts.get(position).getData();
        return null;
    }

    public void clearList() {
        this.posts.clear();
        notifyDataSetChanged();
    }

    public String getLastName() {
        if (posts.size() == 0)
            return null;
        return posts.get(posts.size() - 1).getData().getName();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivThumbnail)
        ImageView ivTumbnail;

        @BindView(R.id.tvName)
        TextView tvName;

        @BindView(R.id.tvContent)
        TextView tvContent;

        PostViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
