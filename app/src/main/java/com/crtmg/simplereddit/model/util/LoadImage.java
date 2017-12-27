package com.crtmg.simplereddit.model.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.util.LruCache;
import android.webkit.URLUtil;
import android.widget.ImageView;

import com.crtmg.simplereddit.R;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by argi on 12/27/17.
 */

public class LoadImage extends AsyncTask<String, Void, Bitmap> {


    private final WeakReference<ImageView> imageViewReference;
    private final WeakReference<LruCache<String, Bitmap>> lruCacheWeakReference;

    public LoadImage(ImageView imageView, LruCache<String, Bitmap> lruCache) {
        imageViewReference = new WeakReference<ImageView>(imageView);
        lruCacheWeakReference = new WeakReference<LruCache<String, Bitmap>>(lruCache);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        if (params.length > 0)
            try {
                String url = params[0];

                if (!URLUtil.isValidUrl(url))
                    return null;

                Bitmap bitmap = lruCacheWeakReference.get().get(params[0]);
                if (bitmap == null) {
                    bitmap = downloadBitmap(url);
                    lruCacheWeakReference.get().put(params[0], bitmap);
                }
                return bitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }

        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (isCancelled()) {
            bitmap = null;
        }

        ImageView imageView = imageViewReference.get();
        if (imageView != null) {
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            } else {
                Drawable placeholder = imageView.getContext().getResources().getDrawable(R.drawable.ic_launcher_background);
                imageView.setImageDrawable(placeholder);
            }
        }
    }

    private Bitmap downloadBitmap(String url) {
        HttpURLConnection urlConnection = null;
        try {
            URL uri = new URL(url);
            urlConnection = (HttpURLConnection) uri.openConnection();
            int statusCode = urlConnection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                return null;
            }

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                return bitmap;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("ImageDownloader", "Error downloading image from " + url);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }
}
