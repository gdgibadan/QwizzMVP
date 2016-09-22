package com.ibadan.gdg.qwizzmvp.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Adapted from github.com/romannurik/muzei/
 * <p/>
 * Also see https://code.google.com/p/android/issues/detail?id=9904
 */
class Fonts {

    private Fonts() { }

    private static final Map<String, Typeface> typefaceCache = new HashMap<String, Typeface>();

    static Typeface get(Context context, @Nullable String path) {

        synchronized (typefaceCache) {

            if (!TextUtils.isEmpty(path) && !typefaceCache.containsKey(path)) {

                AssetManager assetManager = context.getApplicationContext().getAssets();
                Typeface tf = Typeface.createFromAsset(assetManager, path);
                typefaceCache.put(path, tf);
            }

            return typefaceCache.get(path);
        }
    }
}

