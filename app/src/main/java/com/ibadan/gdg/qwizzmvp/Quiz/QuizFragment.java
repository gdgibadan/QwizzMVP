package com.ibadan.gdg.qwizzmvp.quiz;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibadan.gdg.qwizzmvp.R;
import com.ibadan.gdg.qwizzmvp.data.model.Capital;
import com.ibadan.gdg.qwizzmvp.data.model.Country;
import com.ibadan.gdg.qwizzmvp.data.model.Results;
import com.ibadan.gdg.qwizzmvp.databinding.FragmentQuizBinding;
import com.ibadan.gdg.qwizzmvp.results.ResultsActivity;
import com.ibadan.gdg.qwizzmvp.util.ImeUtils;

/**
 * A placeholder fragment containing a simple view.
 */
public class QuizFragment extends Fragment implements QuizContract.View {

    public static final int TIME_IN_SECONDS = 40; //seconds
    public static final int TIME_IN_MILLIS = TIME_IN_SECONDS * 1000; //millis

    public static final int PROGRESS_MAX = 10000;

    public static final int DESIRED_FPS = 60;
    public static final int DECREMENT_PER_FRAME = PROGRESS_MAX / (DESIRED_FPS * TIME_IN_SECONDS);
    public static final int FRAME_DELAY_MILLIS = 1000 * (1 / DESIRED_FPS); //millis

    QuizContract.Presenter presenter;
    FragmentQuizBinding binding;

    private int progress;

    private String currentId;

    private Runnable countDown = new Runnable() {
        @Override
        public void run() {

            progress -= DECREMENT_PER_FRAME;

            if (progress >= 0) {

                binding.progress.setProgress(progress);
                binding.progress.postDelayed(this, FRAME_DELAY_MILLIS);

            } else {

                presenter.onCountDownFinished();
            }
        }
    };

    public QuizFragment() {
    }

    public static QuizFragment newInstance() {
        return new QuizFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.answerEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (TextUtils.isEmpty(s)) {

                    if (binding.divider.getVisibility() == View.VISIBLE)
                        binding.divider.setVisibility(View.GONE);

                    if (binding.buttonAnswer.getVisibility() == View.VISIBLE)
                        binding.buttonAnswer.setVisibility(View.GONE);

                } else {

                    if (binding.divider.getVisibility() == View.GONE)
                        binding.divider.setVisibility(View.VISIBLE);

                    if (binding.buttonAnswer.getVisibility() == View.GONE)
                        binding.buttonAnswer.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.buttonSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presenter != null) presenter.onSkipQuestion();
            }
        });

        binding.buttonAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presenter != null) {
                    String text = binding.answerEdit.getText().toString();
                    Capital capital = new Capital(currentId, text);

                    presenter.onAnswerQuestion(capital);
                }
            }
        });

        // TODO: 21/09/2016 Restore persisted state (progress and delay runnable)
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void showProgressIndicator(boolean active) {

    }

    @Override
    public void showQuestion(Country country) {
        currentId = country.getId();

        // Hack to fix truncated italic character
        binding.questionText.setText(country.getName() + " ");
        binding.answerEdit.setText("");
    }

    @Override
    public void pauseTimer() {
        binding.progress.removeCallbacks(countDown);
    }

    @Override
    public void resumeTimer() {
        binding.progress.postDelayed(countDown, FRAME_DELAY_MILLIS);
    }

    @Override
    public void restartTimer() {
        progress = PROGRESS_MAX;

        binding.progress.setMax(PROGRESS_MAX);
        binding.progress.setProgress(progress);

        binding.progress.postDelayed(countDown, FRAME_DELAY_MILLIS);
    }

    @Override
    public void showScore(final Results results) {

        //  disable input
        binding.answerEdit.setEnabled(false);

        // remove keyboard
        ImeUtils.hideIme(binding.answerEdit);

        // launch screen after delay
        Intent intent = new Intent(getContext(), ResultsActivity.class);
        intent.putExtra(Results.BUNDLE_KEY, results);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void setPresenter(QuizContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
