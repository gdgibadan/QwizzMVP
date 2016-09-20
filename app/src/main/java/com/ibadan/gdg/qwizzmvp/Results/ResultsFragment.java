package com.ibadan.gdg.qwizzmvp.Results;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibadan.gdg.qwizzmvp.Data.User;
import com.ibadan.gdg.qwizzmvp.R;


public class ResultsFragment extends Fragment implements ResultsContract.View {
    View v;
    Context context;
    ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        progressDialog = new ProgressDialog(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_results, container, false);
        return v;
    }


    @Override
    public void showProgressIndicator(boolean active) {
        if (active) { progressDialog.show(); } else { progressDialog.cancel(); }
    }

    @Override
    public void showScore(int score, @Nullable User user) {

    }

    @Override
    public void showAccountCreateDialog() {

    }
}
