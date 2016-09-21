package com.ibadan.gdg.qwizzmvp.quiz;

import android.support.annotation.NonNull;

import com.ibadan.gdg.qwizzmvp.data.Answer;
import com.ibadan.gdg.qwizzmvp.data.Question;

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
