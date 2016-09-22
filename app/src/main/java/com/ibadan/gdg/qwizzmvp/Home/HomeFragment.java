package com.ibadan.gdg.qwizzmvp.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ibadan.gdg.qwizzmvp.R;
import com.ibadan.gdg.qwizzmvp.quiz.QuizActivity;

public class HomeFragment extends Fragment implements HomeContract.View {

    HomeContract.Presenter presenter;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {

        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button start = (Button) view.findViewById(R.id.buttonStart);
        Button highscore = (Button) view.findViewById(R.id.buttonHighscore);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presenter != null) presenter.onStartGame();
            }
        });

        highscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presenter != null) presenter.onShowHighscores();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showProgressIndicator(boolean active) {

    }

    @Override
    public void showStartGame() {
        Intent intent = new Intent(getContext(), QuizActivity.class);
        startActivity(intent);
    }

    @Override
    public void showHighscore() {

    }
}
