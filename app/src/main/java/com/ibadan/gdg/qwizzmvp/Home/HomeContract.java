package com.ibadan.gdg.qwizzmvp.home;

import com.ibadan.gdg.qwizzmvp.BasePresenter;
import com.ibadan.gdg.qwizzmvp.BaseView;

/**
 * Created by Hamza Fetuga on 9/20/2016.
 */
public interface HomeContract {

    interface View extends BaseView<Presenter> {

        void showProgressIndicator(boolean active);
        void showStartGame();
        void showHighscore();
    }

    interface Presenter extends BasePresenter {

        void onStartGame();
        void onShowHighscores();
    }
}
