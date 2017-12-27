package com.crtmg.simplereddit.model.adapter;

import com.crtmg.simplereddit.model.reddit.Children;
import com.crtmg.simplereddit.model.reddit.Post;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by argi on 12/27/17.
 */
public class PostsListAdapterTest {
    private final String name = "Name";
    private PostsListAdapter adapter;
    private List<Children> list;

    @Before
    public void init(){
        adapter = PostsListAdapter.getInstance();
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            Post post = new Post();
            post.setId(""+i);
            post.setName(name+i);
            Children children = new Children();
            children.setData(post);
            list.add(children);
        }
        adapter.newPosts(list);
    }
    @Test
    public void getItemOnPosition() throws Exception {
        int i = 0;
        for (Children children: list) {
            String requestedID = children.getData().getId();
            Assert.assertTrue(adapter.getItemOnPosition(i).getId().equalsIgnoreCase("" + requestedID));
            i++;
        }
    }


    @Test
    public void getLastName() throws Exception {
        Assert.assertTrue(adapter.getLastName().equalsIgnoreCase(name+(list.size()-1)));
    }

    @After
    public void dealloc(){
        adapter = null;
        list.clear();
        list = null;
    }


}