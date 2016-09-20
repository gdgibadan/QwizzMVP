package com.ibadan.gdg.qwizzmvp.Quiz;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ibadan.gdg.qwizzmvp.Data.Answer;
import com.ibadan.gdg.qwizzmvp.Data.Question;
import com.ibadan.gdg.qwizzmvp.Data.User;

/**
 * Created by Hamza Fetuga on 9/20/2016.
 */
public interface QuizContract {

    interface View{

        void showProgressIndicator(boolean active);

        void displayQuestion(Question question);

        void showProgress();

        void incrementTimer();

        void stopTimer();

        void showCurrentScore(int score);

    }

    interface UserActionsListener{

        // scrambles the list of countries
        void setupQuestions();

        // skips current question
        void skipQuestion(int questionId);

        // submits the answers and grades
        void submitAnswer(@NonNull Question question,@NonNull Answer answer);

        //get the next question
        Question getQuestion();
    }
}
