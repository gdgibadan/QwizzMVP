package com.ibadan.gdg.qwizzmvp.util;

import android.support.annotation.Nullable;

/**
 * Created by Efe on 21/09/2016.
 */

public final class Preconditions {

    public static <T> T checkNotNull(T reference) {
        if(reference == null) {
            throw new NullPointerException();
        } else {
            return reference;
        }
    }

    public static <T> T checkNotNull(T reference, @Nullable Object errorMessage) {
        if(reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        } else {
            return reference;
        }
    }
}
