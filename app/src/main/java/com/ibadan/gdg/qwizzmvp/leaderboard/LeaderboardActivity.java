package com.ibadan.gdg.qwizzmvp.leaderboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ibadan.gdg.qwizzmvp.R;

public class LeaderboardActivity extends AppCompatActivity implements LeaderboardContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        LeaderboardPresenter presenter = new LeaderboardPresenter(this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMyUsername(String username) {
    }

    @Override
    public void showMyScore(String username) {

    }

    @Override
    public void showScores() {

    }

    @Override
    public void setPresenter(LeaderboardContract.Presenter presenter) {

    }
}
