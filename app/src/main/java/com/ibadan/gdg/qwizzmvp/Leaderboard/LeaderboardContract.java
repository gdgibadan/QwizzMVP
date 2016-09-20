package com.ibadan.gdg.qwizzmvp.Leaderboard;

import android.content.Context;

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
