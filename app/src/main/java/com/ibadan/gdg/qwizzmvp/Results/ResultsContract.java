package com.ibadan.gdg.qwizzmvp.results;

import android.support.annotation.Nullable;

import com.ibadan.gdg.qwizzmvp.BasePresenter;
import com.ibadan.gdg.qwizzmvp.BaseView;
import com.ibadan.gdg.qwizzmvp.data.model.Results;
import com.ibadan.gdg.qwizzmvp.data.model.User;

/**
 * Created by Hamza Fetuga on 9/20/2016.
 */
public interface ResultsContract {

    interface View extends BaseView<Presenter> {

        void showProgressIndicator(boolean active);

        void showResults(Results results);

        void showAccountCreateDialog();

        void showSignInPrompt();

        void hideSignInPrompt();
    }

    interface Presenter extends BasePresenter{

        void saveScore(int score, @Nullable User user);

        void onCreateAccount();

        void checkUsernameUnique(String username);

        void tryAgain();
    }
}
