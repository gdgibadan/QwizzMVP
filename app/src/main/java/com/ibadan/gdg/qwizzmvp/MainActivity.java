package com.ibadan.gdg.qwizzmvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ibadan.gdg.qwizzmvp.Home.HomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.container) != null){
            if (savedInstanceState != null) {
                return;
            }

            HomeFragment homeFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment,
                    HomeFragment.TAG).commit();

        }
    }


}
