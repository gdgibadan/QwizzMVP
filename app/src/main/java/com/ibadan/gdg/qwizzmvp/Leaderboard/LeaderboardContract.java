package com.ibadan.gdg.qwizzmvp.leaderboard;

/**
 * Created by Hamza Fetuga on 9/20/2016.
 */
public interface LeaderboardContract {

    interface View{

        void showProgressIndicator(boolean active);

        void showLeaderboard();

    }

    interface UserActionsListener{

        void getScores();

        void shareScores(String score, int position);
    }

}
