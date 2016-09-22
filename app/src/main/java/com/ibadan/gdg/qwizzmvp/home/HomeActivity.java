package com.ibadan.gdg.qwizzmvp.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ibadan.gdg.qwizzmvp.util.ActivityUtils;
import com.ibadan.gdg.qwizzmvp.R;

public class HomeActivity extends AppCompatActivity {

    HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        // Retrieve the view
        HomeFragment fragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.content);

        if (fragment == null) {
            // Create and add the fragment
            fragment = HomeFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.content);
        }

        // Create the presenter
        presenter = new HomePresenter(fragment);
    }
}
