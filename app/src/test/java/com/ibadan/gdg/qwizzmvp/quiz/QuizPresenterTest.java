package com.ibadan.gdg.qwizzmvp.quiz;

import android.content.Context;

import com.ibadan.gdg.qwizzmvp.data.model.Country;
import com.ibadan.gdg.qwizzmvp.data.model.CountryCapitalPair;
import com.ibadan.gdg.qwizzmvp.data.model.Results;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Created by Hamza Fetuga on 9/26/2016.
 */
public class QuizPresenterTest {

    @Mock
    Context context;
    @Mock
    QuizContract.View view;
    @Mock
    CountryCapitalPair countryCapitalPair;
    @Captor
    ArgumentCaptor<Boolean> progressCaptor;
    QuizPresenter presenter;
    @Mock
    QuizPresenter mockPresenter;
    QuizPresenter spyPresenter;
    List<CountryCapitalPair> questions;
    @Mock
    Results results;
    @Captor
    ArgumentCaptor<Boolean> endProgressCaptor;
    @Captor
    ArgumentCaptor<Integer> markCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new QuizPresenter(context, view);
        spyPresenter = spy(presenter);
    }

    /**1. Record users score
     * 2. Get the next country
     * 3. Display the next country
     */
    @Test
    public void testOnSkipQuestion() throws RuntimeException{

        doNothing().when(spyPresenter)
                .recordScore(Mockito.any(CountryCapitalPair.class),
                        Mockito.anyInt());
        doReturn(countryCapitalPair).when(spyPresenter).getNextCountry();

        spyPresenter.onSkipQuestion();

        InOrder inOrder = inOrder(spyPresenter, view);
        inOrder.verify(spyPresenter).recordScore(Mockito.any(CountryCapitalPair.class),
                            markCaptor.capture());
        assertTrue("second argument has to be -1", markCaptor.getValue() == -1);
        inOrder.verify(view).showQuestion(spyPresenter.getNextCountry().asCountry());
    }


    @Test
    public void testOnAnswerQuestion() throws Exception {

    }

    /**
     * 1. Check if time is paused
     * 2. Check if progress indicator is shown
     * 3. Calculate results
     * 4. Check if progress indicator is hidden
     * 5. Check if score is shown
     * @throws Exception
     */
    @Test
    public void testOnCountDownFinished() throws Exception {

        InOrder inOrder = inOrder(view, spyPresenter);
        doReturn(results).when(spyPresenter).calculateScores();
        spyPresenter.onCountDownFinished();

        inOrder.verify(view).pauseTimer();
        inOrder.verify(view).showProgressIndicator(progressCaptor.capture());
        assertEquals(progressCaptor.getValue(), true);

        inOrder.verify(spyPresenter).calculateScores();
        inOrder.verify(view).showProgressIndicator(progressCaptor.capture());
        assertEquals(progressCaptor.getValue(), false);
        inOrder.verify(view).showScore(Mockito.any(Results.class));


    }

    /**
     1. Check if progress indicator was shown
     2. Check if questions were gotten and shuffled (i.e not null)
     3. Check if first question was returned (i.e not null)
     4. Check if question was shown
     5. Check if progress indicator was hidden
     6. Check if timer was started
     **/
    @Test
    public void testStart() throws Exception {

        questions = new ArrayList<>();
        prepareQuestionsList();
        doReturn(questions).when(spyPresenter).getAndShuffleQuestions();
        doReturn(countryCapitalPair).when(spyPresenter).getNextCountry();

        spyPresenter.start();

        InOrder inOrder = inOrder(view);
        inOrder.verify(view).showProgressIndicator(progressCaptor.capture());
        assertEquals(progressCaptor.getValue(), true);
        inOrder.verify(view).showQuestion(countryCapitalPair.asCountry());
        inOrder.verify(view).showProgressIndicator(progressCaptor.capture());
        assertEquals(progressCaptor.getValue(), false);
        inOrder.verify(view).restartTimer();

    }

    /** Prepares list of mock CountryCapitalPairs
     *
     */
    public void prepareQuestionsList(){
        int randInt = 1 + (int)(Math.random() * 10);
        for (int i = 0; i < randInt; i++) {
            questions.add(countryCapitalPair);
        }
    }
}