package com.ibadan.gdg.qwizzmvp.home;

import android.app.Instrumentation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.ibadan.gdg.qwizzmvp.util.Preconditions.checkNotNull;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;


/**
 * Created by Hamza Fetuga on 9/23/2016.
 */
public class HomePresenterTest {

    @Mock
    private HomeContract.View mView;

    private HomePresenter presenter;

    @Before
    public void setupHomePresenter(){
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        presenter = new HomePresenter(mView);
    }


    @Test
    public void checkHomePresenter_NotNull(){
        checkNotNull(presenter, "Presenter is null");
        verify(mView).setPresenter(presenter);
    }
}