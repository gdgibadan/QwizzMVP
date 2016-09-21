package com.ibadan.gdg.qwizzmvp.results;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ibadan.gdg.qwizzmvp.data.User;

/**
 * Created by Hamza Fetuga on 9/20/2016.
 */
public interface ResultsContract {

    interface View{

        void showProgressIndicator(boolean active);

        void showScore(int score, @Nullable User user);

        void showAccountCreateDialog();

    }

    interface UserActionsListener{

        void saveScore(int score, @Nullable User user);

        void tryAgain();

        void createAccount(@NonNull User user);

        void isUserUnique(@NonNull User user);
    }
}
