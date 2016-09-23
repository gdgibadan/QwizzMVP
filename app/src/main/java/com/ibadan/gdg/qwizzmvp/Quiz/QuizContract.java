package com.ibadan.gdg.qwizzmvp.quiz;

import android.support.annotation.NonNull;

import com.ibadan.gdg.qwizzmvp.BasePresenter;
import com.ibadan.gdg.qwizzmvp.BaseView;
import com.ibadan.gdg.qwizzmvp.data.model.Capital;
import com.ibadan.gdg.qwizzmvp.data.model.Country;
import com.ibadan.gdg.qwizzmvp.data.model.Results;

/**
 * Created by Hamza Fetuga on 9/20/2016.
 */
public interface QuizContract {

    interface View extends BaseView<Presenter> {


        void showProgressIndicator(boolean active);

        void showQuestion(Country country);

        void pauseTimer();

        void resumeTimer();

        void restartTimer();

        void showScore(Results results);
    }

    interface Presenter extends BasePresenter {

        // skips current question
        void onSkipQuestion();

        // submits the answers and grades
        void onAnswerQuestion(@NonNull Capital capital);

        void onCountDownFinished();
    }
}
