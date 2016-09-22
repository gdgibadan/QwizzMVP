package com.ibadan.gdg.qwizzmvp.results;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import com.ibadan.gdg.qwizzmvp.data.ResultsProvider;
import com.ibadan.gdg.qwizzmvp.data.model.User;

import static com.ibadan.gdg.qwizzmvp.util.Preconditions.checkNotNull;

/**
 * Created by Hamza Fetuga on 9/20/2016.
 */
public class ResultsPresenter implements ResultsContract.Presenter {

    private final Context context;
    private final ResultsProvider provider;
    private final ResultsContract.View view;

    private User currentUser;

    public ResultsPresenter(Context appContext, ResultsProvider provider, ResultsContract.View view) {

        this.context = checkNotNull(appContext.getApplicationContext(), "Context cannot be null");
        this.provider = checkNotNull(provider, "Results.Provider cannot be null");
        this.view = checkNotNull(view, "Results.View cannot be null");

        this.view.setPresenter(this);
    }

    @Override
    public void saveScore(int score, @Nullable User user) {

    }

    @Override
    public void onCreateAccount() {
        view.showAccountCreateDialog();
    }

    @Override
    public void checkUsernameUnique(String username) {

    }

    @Override
    public void tryAgain() {

    }

    @Override
    public void start() {
        view.showProgressIndicator(true);

        currentUser = getUser(context);

        if (currentUser == null)
            view.showSignInPrompt();
        else
            view.hideSignInPrompt();

        view.showResults(provider.getResults());

        view.showProgressIndicator(false);
    }

    private User getUser(Context context) {

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String username = pref.getString(User.PREF_KEY, null);

        return username == null ? null : new User(username);
    }
}
