package com.ibadan.gdg.qwizzmvp.quiz;

import android.content.Context;
import android.provider.ContactsContract;

import com.ibadan.gdg.qwizzmvp.data.model.Capital;
import com.ibadan.gdg.qwizzmvp.data.model.Country;
import com.ibadan.gdg.qwizzmvp.data.model.CountryCapitalPair;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;

import java.util.Map;
import java.util.Stack;

import static com.ibadan.gdg.qwizzmvp.util.Preconditions.checkNotNull;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Created by Hamza Fetuga on 9/23/2016.
 */
public class QuizPresenterTest {

    @Mock
    private Context mContext;

    @Mock
    private QuizContract.View mView;

    @Mock
    private Map<CountryCapitalPair, Integer> scores;

    @Mock
    private Stack<CountryCapitalPair> state;

    @Mock
    private CountryCapitalPair current;

    @Mock
    private Capital capital;

    QuizPresenter mPresenter;

    @Before
    private void setUpQuizPresenter(){
        MockitoAnnotations.initMocks(this);

        mPresenter = new QuizPresenter(mContext, mView);
    }

    @Test
    private void checkQuizPresenter_NotNull(){
        checkNotNull(mPresenter, "Quiz Presenter is null!");
    }


    @Test
    public void testOnSkipQuestion() throws Exception {
        verify(scores).put(current, -1);
        current = verify(state).pop();
        verify(mView).showQuestion(current.asCountry());
    }

    @Test
    public void testOnAnswerQuestion() throws Exception {

    }


    @Test
    public void testOnCountDownFinished() throws Exception {
        verify(mView).showProgressIndicator(true);

    }

    @Test
    public void testStart() throws Exception {

    }
}