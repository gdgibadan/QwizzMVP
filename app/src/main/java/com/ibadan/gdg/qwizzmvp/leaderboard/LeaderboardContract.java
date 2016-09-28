package com.ibadan.gdg.qwizzmvp.leaderboard;

import com.ibadan.gdg.qwizzmvp.BasePresenter;
import com.ibadan.gdg.qwizzmvp.BaseView;

/**
 * Created by Hamza Fetuga on 9/28/2016.
 */
public interface LeaderboardContract {

    interface View extends BaseView<Presenter> {

        void showProgressIndicator(boolean active);

        void displayTop(int numOfScores);

        void displayUserScore();

        void onShareScore();

    }

    interface Presenter extends BasePresenter {

        void getScores();

        void getUserScore();

        void shareScore();

    }

}
