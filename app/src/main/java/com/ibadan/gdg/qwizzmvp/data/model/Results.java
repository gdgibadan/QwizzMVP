package com.ibadan.gdg.qwizzmvp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Efe on 21/09/2016.
 */

public class Results implements Parcelable {

    public static final String BUNDLE_KEY = "results";
    public static final Parcelable.Creator<Results> CREATOR = new Parcelable.Creator<Results>() {
        @Override
        public Results createFromParcel(Parcel source) {
            return new Results(source);
        }

        @Override
        public Results[] newArray(int size) {
            return new Results[size];
        }
    };

    private int skipped = 0;
    private int attempted = 0;
    private int correct = 0;
    private int incorrect = 0;
    private Map<CountryCapitalPair, Integer> scores;

    public Results(Map<CountryCapitalPair, Integer> scores) {
        this.scores = scores;
    }

    protected Results(Parcel in) {
        this.skipped = in.readInt();
        this.attempted = in.readInt();
        this.correct = in.readInt();
        this.incorrect = in.readInt();
        int scoresSize = in.readInt();
        this.scores = new HashMap<CountryCapitalPair, Integer>(scoresSize);
        for (int i = 0; i < scoresSize; i++) {
            CountryCapitalPair key = in.readParcelable(CountryCapitalPair.class.getClassLoader());
            Integer value = (Integer) in.readValue(Integer.class.getClassLoader());
            this.scores.put(key, value);
        }
    }

    public Map<CountryCapitalPair, Integer> getScores() {
        return scores;
    }

    public int getIncorrect() {
        return incorrect;
    }

    public int getCorrect() {
        return correct;
    }

    public int getAttempted() {
        return attempted;
    }

    public int getSkipped() {
        return skipped;
    }

    public void compute(boolean addSkippedToAttempted) {

        if (this.scores == null) throw new RuntimeException("Must set scores before computing");

        for (Integer i : scores.values()) {

            if (i == -1) {
                skipped++;
                if (addSkippedToAttempted) attempted++;
            }
            if (i == 0) {
                incorrect++;
                attempted++;
            }
            if (i == 1) {
                correct++;
                attempted++;
            }
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.skipped);
        dest.writeInt(this.attempted);
        dest.writeInt(this.correct);
        dest.writeInt(this.incorrect);
        dest.writeInt(this.scores.size());
        for (Map.Entry<CountryCapitalPair, Integer> entry : this.scores.entrySet()) {
            dest.writeParcelable(entry.getKey(), flags);
            dest.writeValue(entry.getValue());
        }
    }
}
