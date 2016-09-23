package com.ibadan.gdg.qwizzmvp.leaderboard;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ibadan.gdg.qwizzmvp.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class LeaderboardActivityFragment extends Fragment {

    public LeaderboardActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_leaderboard, container, false);
    }
}
