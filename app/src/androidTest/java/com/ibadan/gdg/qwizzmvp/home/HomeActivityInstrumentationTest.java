package com.ibadan.gdg.qwizzmvp.home;

import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ibadan.gdg.qwizzmvp.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by Hamza Fetuga on 9/27/2016.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class HomeActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<HomeActivity> homeActivityActivityTestRule
            = new ActivityTestRule<HomeActivity>(HomeActivity.class);

    @Test
    public void launchActivity_startQuiz(){
        onView(withId(R.id.buttonStart)).perform(click());
    }

}