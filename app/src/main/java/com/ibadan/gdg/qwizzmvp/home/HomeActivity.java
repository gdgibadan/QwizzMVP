package com.ibadan.gdg.qwizzmvp.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ibadan.gdg.qwizzmvp.R;
import com.ibadan.gdg.qwizzmvp.util.ActivityUtils;

public class HomeActivity extends AppCompatActivity {

    HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


//        /**
//         *  Helps add the homeFragment to this activity
//         *  Can be abstracted out
//         */
//        if (findViewById(R.id.content) != null){
//            HomeFragment fragment = new HomeFragment();
//            getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment,
//                    HomeFragment.TAG).commit();
//        }

        // Retrieve the view
        HomeFragment fragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.content);

        if (fragment == null){
            //Create and add the fragment
            fragment = new HomeFragment();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment,
                    R.id.content);
        }

        presenter = new HomePresenter(fragment);
    }
}
