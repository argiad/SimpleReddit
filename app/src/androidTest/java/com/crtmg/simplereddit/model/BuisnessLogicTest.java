package com.crtmg.simplereddit.model;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.test.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * Created by argi on 12/27/17.
 */

public class BuisnessLogicTest {

    private final String reddit = "test_reddit";
    private final String filter = "test_filter";

    BuisnessLogic buisnessLogic;

    @Before
    public void setUp(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        buisnessLogic =  ((SimpleReddit) appContext.getApplicationContext()).buisnessLogic;
    }

    @Test
    public void isBuisnesLogicPresent() throws Exception{
        Assert.assertNotNull(buisnessLogic);
    }

    @Test
    public void setNewRoute() throws Exception {
        buisnessLogic.setNewRoute(reddit, filter);

        Field privateReddits = BuisnessLogic.class.getDeclaredField("reddits");
        Field privateFilter = BuisnessLogic.class.getDeclaredField("filter");

        privateReddits.setAccessible(true);
        privateFilter.setAccessible(true);

        String cReddits = (String) privateReddits.get(buisnessLogic);
        String cFilter = (String) privateFilter.get(buisnessLogic);

        Assert.assertTrue(reddit.endsWith(cReddits));
        Assert.assertTrue(filter.equalsIgnoreCase(cFilter));
    }

}