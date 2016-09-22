package com.ibadan.gdg.qwizzmvp.results;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ibadan.gdg.qwizzmvp.R;
import com.ibadan.gdg.qwizzmvp.data.model.CountryCapitalPair;
import com.ibadan.gdg.qwizzmvp.data.model.Results;
import com.ibadan.gdg.qwizzmvp.databinding.FragmentResultsBinding;
import com.ibadan.gdg.qwizzmvp.databinding.ItemResultBinding;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class ResultsFragment extends Fragment implements ResultsContract.View {

    ResultsContract.Presenter presenter;

    FragmentResultsBinding binding;
    private ProgressDialog pd;

    public ResultsFragment() {
        // Required empty public constructor
    }

    public static ResultsFragment newInstance() {

        return new ResultsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_results, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presenter != null) presenter.onCreateAccount();
            }
        });

        binding.list.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void showProgressIndicator(boolean active) {

        if (active) {
            if (pd == null) {
                pd = new ProgressDialog(getContext());
                pd.setMessage("Loading...");
            }
            pd.show();

        } else if (pd != null) pd.dismiss();
    }

    @Override
    public void showResults(Results results) {

        ResultsAdapter adapter = new ResultsAdapter(results);
        binding.list.swapAdapter(adapter, false);

        binding.summary.setText(String.format(Locale.US, "%d skipped : %d attempts : %d correct",
                results.getSkipped(),
                results.getAttempted(),
                results.getCorrect()
        ));
    }

    @Override
    public void showAccountCreateDialog() {

        // TODO: 22/09/2016 Animate diolog appearance

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppTheme_Dialog)
                .setTitle("Create an account");

        LayoutInflater inflater = LayoutInflater.from(builder.getContext());
        View v = inflater.inflate(R.layout.dialog_create_account, null, false);
        final EditText e = (EditText) v.findViewById(R.id.edit);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String text = e.getText().toString();
                if (!TextUtils.isEmpty(text) && presenter != null) {
                    presenter.submitUsername(text);
                }
            }
        });
        builder.setView(v);
        builder.show();
    }

    @Override
    public void showSignInPrompt() {
        binding.signIn.setVisibility(View.VISIBLE);
        binding.marginTop.setVisibility(View.GONE);
    }

    @Override
    public void hideSignInPrompt() {
        binding.signIn.setVisibility(View.GONE);
        binding.marginTop.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String errorMessage) {
        new AlertDialog.Builder(getContext()).setMessage(errorMessage).show();
    }

    @Override
    public void clearError() {

    }

    @Override
    public void setPresenter(ResultsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ViewHolder> {

        private Results results;
        ArrayList<CountryCapitalPair> data;

        ResultsAdapter(Results results) {

            this.results = results;
            this.data = new ArrayList<>(results.getScores().keySet());

            // remove skipped items
            Iterator<CountryCapitalPair> itr = data.iterator();

            while (itr.hasNext()) {
                CountryCapitalPair item = itr.next();
                if (results.getScores().get(item) == -1) itr.remove();
            }
        }

        @Override
        public ResultsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(parent.getContext());

            ItemResultBinding binding =
                    DataBindingUtil.inflate(inflater, R.layout.item_result, parent, false);

            return new ViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(ResultsAdapter.ViewHolder holder, int position) {

            CountryCapitalPair item = data.get(position);
            int score = results.getScores().get(item);

            holder.binding.questionText.setText(item.getCountry() + " ");
            holder.binding.answerText.setText(item.getUserCapital() + " ");

            Drawable wrong = AppCompatDrawableManager.get()
                    .getDrawable(binding.getRoot().getContext(), R.drawable.wrong);
            Drawable right = AppCompatDrawableManager.get()
                    .getDrawable(binding.getRoot().getContext(), R.drawable.correct);

            holder.binding.answerText.setCompoundDrawablesWithIntrinsicBounds(null, null,
                    score == 0 ? wrong : score == 1 ? right : null, null);

            if (score == 0) holder.binding.correctionText.setText(item.getCapital() + " ");
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            ItemResultBinding binding;

            public ViewHolder(ItemResultBinding binding) {
                super(binding.getRoot());

                this.binding = binding;
                binding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // handle click
                    }
                });
            }
        }
    }
}
