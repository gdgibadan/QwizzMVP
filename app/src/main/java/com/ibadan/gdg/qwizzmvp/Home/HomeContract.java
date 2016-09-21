package com.ibadan.gdg.qwizzmvp.home;

/**
 * Created by Hamza Fetuga on 9/20/2016.
 */
public interface HomeContract {

    interface View{

        void showProgressIndicator(boolean active);

    }

    interface UserActionsListener{

        void startGame();
    }
}
