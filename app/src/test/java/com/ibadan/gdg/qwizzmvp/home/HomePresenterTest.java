package com.ibadan.gdg.qwizzmvp.home;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Created by Hamza Fetuga on 9/26/2016.
 */
public class HomePresenterTest {

    @Mock
    HomeContract.View view;
    HomePresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new HomePresenter(view);
    }

    @Test
    public void testOnStartGame() throws Exception {
        presenter.onStartGame();
        verify(view).showStartGame();
    }

    @Test
    public void testOnShowHighscores() throws Exception {
        presenter.onShowHighscores();
        verify(view).showHighscore();
    }

    @Test
    public void testStart() throws Exception {

    }
}