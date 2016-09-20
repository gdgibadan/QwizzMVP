package com.ibadan.gdg.qwizzmvp.Quiz;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibadan.gdg.qwizzmvp.Data.Question;
import com.ibadan.gdg.qwizzmvp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizFragment extends Fragment implements QuizContract.View{
    View v;
    Context context;
    ProgressDialog progressDialog;

    public QuizFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        progressDialog = new ProgressDialog(context);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_quiz, container, false);
        return v;
    }

    @Override
    public void showProgressIndicator(boolean active) {
        if (active) { progressDialog.show(); } else { progressDialog.cancel(); }
    }

    @Override
    public void displayQuestion(Question question) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void incrementTimer() {

    }

    @Override
    public void stopTimer() {

    }

    @Override
    public void showCurrentScore(int score) {

    }
}
