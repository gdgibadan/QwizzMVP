package com.ibadan.gdg.qwizzmvp.data;

import android.content.Context;

/**
 * Created by Efe on 21/09/2016.
 */

public abstract class Repository {

    public static Repository provideRepository (Context appContext) {

        return new Repository() {};
    }
}
