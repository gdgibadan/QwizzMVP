package com.ibadan.gdg.qwizzmvp.results;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ibadan.gdg.qwizzmvp.R;
import com.ibadan.gdg.qwizzmvp.data.ResultsProvider;
import com.ibadan.gdg.qwizzmvp.data.model.Results;
import com.ibadan.gdg.qwizzmvp.util.ActivityUtils;

public class ResultsActivity extends AppCompatActivity {

    private ResultsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Retrieve the view
        ResultsFragment fragment = (ResultsFragment) getSupportFragmentManager().findFragmentById(R.id.content);

        if (fragment == null) {

            // Create and add the fragment
            fragment = ResultsFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.content);
        }

        final Results results = getIntent().getParcelableExtra(Results.BUNDLE_KEY);
        ResultsProvider provider = new ResultsProvider() {
            @Override
            public Results getResults() {
                return results;
            }
        };

        // Create the presenter
        presenter = new ResultsPresenter(getApplicationContext(), provider, fragment);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
