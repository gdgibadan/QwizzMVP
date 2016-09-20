package com.ibadan.gdg.qwizzmvp.Leaderboard;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibadan.gdg.qwizzmvp.R;


public class LeaderboardFragment extends Fragment implements LeaderboardContract.View {
    View v;
    Context context;
    ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        progressDialog = new ProgressDialog(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_leaderboard, container, false);
        return v;
    }

    @Override
    public void showProgressIndicator(boolean active) {
        if (active) { progressDialog.show(); } else { progressDialog.cancel(); }
    }

    @Override
    public void showLeaderboard() {

    }
}
