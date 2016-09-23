package com.ibadan.gdg.qwizzmvp.home;

import static com.ibadan.gdg.qwizzmvp.util.Preconditions.checkNotNull;

/**
 * Created by Hamza Fetuga on 9/20/2016.
 */
public class HomePresenter implements HomeContract.Presenter {

    private final HomeContract.View view;

    HomePresenter(HomeContract.View view) {
        //this.view = checkNotNull(view, "Home.View cannot be null");
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void onStartGame() {
        view.showStartGame();
    }

    @Override
    public void onShowHighscores() {
        view.showHighscore();
    }

    @Override
    public void start() {

    }
}
