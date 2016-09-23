package com.ibadan.gdg.qwizzmvp.results;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
    public void submitUsername(final String username) {

        view.showProgressIndicator(true);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("highscores/" + username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                view.showProgressIndicator(false);

                if (dataSnapshot.getValue() == null) {
                    // Username does not exist, PROCEED
                    addUsernameAndSaveScore(username);

                } else {
                    // Username exists, SHOW ERROR
                    view.showError("This username already exists.");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                view.showProgressIndicator(false);

                // Network failed or some other error
                view.showError(databaseError.getMessage());
            }
        });
    }

    private void addUsernameAndSaveScore(final String username) {
        view.showProgressIndicator(true);

        int score = provider.getResults().getCorrect();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("highscores/" + username).setValue(score).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                view.showProgressIndicator(false);

                if (task.isSuccessful()) {

                    saveUser(username);

                } else {

                    view.showError("Something went wrong. Could not create account with username: " + username);

                }
            }
        });

        view.hideSignInPrompt();
    }

    private void replaceScoreIfHigher(final String username) {

        final int score = provider.getResults().getCorrect();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("highscores/" + username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Integer previous = dataSnapshot.getValue() != null
                        ? dataSnapshot.getValue(Integer.class)
                        : 0;

                if (score > previous) dataSnapshot.getRef().setValue(score).addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        }
                );
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                view.showError("Score not saved: " + databaseError.getMessage());
            }
        });
    }

    @Override
    public void tryAgain() {

    }

    @Override
    public void start() {
        view.showProgressIndicator(true);

        currentUser = getUser();

        if (currentUser == null) {
            view.showSignInPrompt();

        } else {
            view.hideSignInPrompt();
            replaceScoreIfHigher(currentUser.getUsername());
        }

        view.showResults(provider.getResults());

        view.showProgressIndicator(false);
    }

    private User getUser() {

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String username = pref.getString(User.PREF_KEY, null);
        return username == null ? null : new User(username);
    }

    private void saveUser(String username) {

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        pref.edit().putString(User.PREF_KEY, username).apply();
    }
}
