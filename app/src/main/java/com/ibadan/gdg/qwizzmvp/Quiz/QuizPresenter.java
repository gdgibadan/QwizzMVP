package com.ibadan.gdg.qwizzmvp.quiz;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.ibadan.gdg.qwizzmvp.data.model.Capital;
import com.ibadan.gdg.qwizzmvp.data.model.CountryCapitalPair;
import com.ibadan.gdg.qwizzmvp.data.model.Results;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import static com.ibadan.gdg.qwizzmvp.util.Preconditions.checkNotNull;


/**
 * Created by Hamza Fetuga on 9/20/2016.
 */
public class QuizPresenter implements QuizContract.Presenter {

    private static final int SCORE_MULTIPLIER = 1;
    private static final String FILE = "country-capital-pairs.json";

    private final Gson gson = new GsonBuilder().create();

    private final Context context;
    private final QuizContract.View view;

    private CountryCapitalPair current;
    private Stack<CountryCapitalPair> state;

    // Should add a near accurate guess of the number of questions most users will answer as size
    private Map<CountryCapitalPair, Integer> scores;


    QuizPresenter(Context appContext, QuizContract.View view) {

        this.context = checkNotNull(appContext.getApplicationContext(), "Context cannot be null");
        this.view = checkNotNull(view, "Quiz.View cannot be null");
        this.view.setPresenter(this);
    }

    @Override
    public void onSkipQuestion() {

        // -1 denotes a skipped question that doesnt make it into the final score object
        // This behavior is configurable in settings
        scores.put(current, -1);

        current = state.pop();
        view.showQuestion(current.asCountry());
    }

    @Override
    public void onAnswerQuestion(@NonNull Capital capital) {

        CountryCapitalPair.storeUserCapital(current, capital);
        int score = CountryCapitalPair.checkAnswerCorrect(current, capital);

        // 0 denotes wrong, 1 denotes correct
        scores.put(current, score);

        // get the next questionin the stack and display
        current = state.pop();
        view.showQuestion(current.asCountry());
    }

    @Override
    public void onCountDownFinished() {
        view.pauseTimer();

        view.showProgressIndicator(true);

        // calculate scores
        Results results = new Results(scores);
        results.compute(false);

        view.showProgressIndicator(false);
        view.showScore(results);
    }

    @Override
    public void start() {

        view.showProgressIndicator(true);

        // retrieve questions and randomize
        List<CountryCapitalPair> questions = getAllQuestions();
        Collections.shuffle(questions);

        // init question/answer store. this represents the state of the view
        state = new Stack<>();
        scores = new LinkedHashMap<>();

        state.addAll(questions);

        // display question
        current = state.pop();
        view.showQuestion(current.asCountry());

        view.showProgressIndicator(false);
        view.restartTimer();
    }

    private List<CountryCapitalPair> getAllQuestions() {

        // An optimization would be to hardcode the size of the list array here so there is
        // no array resizing when items exceed the default capacity
        ArrayList<CountryCapitalPair> itemList = new ArrayList<>();

        AssetManager assets = context.getAssets();

        try {

            JsonReader reader = new JsonReader(new InputStreamReader(assets.open(FILE), "UTF-8"));

            // Read file in stream mode
            reader.beginArray();

            while (reader.hasNext()) {

                CountryCapitalPair item = gson.fromJson(reader, CountryCapitalPair.class);
                if (item != null) itemList.add(item);
            }

            reader.close();

        } catch (IOException ignored) {
        }

        return itemList;
    }
}
