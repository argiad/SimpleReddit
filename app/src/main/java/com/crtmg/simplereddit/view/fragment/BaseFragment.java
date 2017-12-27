package com.crtmg.simplereddit.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crtmg.simplereddit.interfaces.NetworkCallback;
import com.crtmg.simplereddit.model.reddit.Children;

import java.util.List;

/**
 * Created by argi on 12/27/17.
 */

public abstract class BaseFragment<T> extends Fragment implements NetworkCallback {


    public static String TAG;

    /**
     * Listener
     */
    protected T mListener;
    Context mContext;
    /**
     * Root View
     */
    View mFragmentRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.setRetainInstance(false);
        mFragmentRootView = inflater.inflate(getLayoutResourceId(), container, false);

        TAG = getClass().getCanonicalName();

        initWidgets(mFragmentRootView);

        return mFragmentRootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // Creates a copy of the current context so it can be accessed from within the listeners
        mContext = context;

        // Check that our hosting activity implements our interface
        try {

            mListener = (T) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement its hosted fragment listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Remove the listener
        mListener = null;

        System.gc();
    }

    @Override
    public void newPosts(List<Children> posts) {
    }

    @Override
    public void returnError(String textToShow) {
    }

    void log(String logString) {
        Log.d(TAG, logString);
    }

    protected abstract int getLayoutResourceId();

    protected abstract void initWidgets(View fragmentView);
}