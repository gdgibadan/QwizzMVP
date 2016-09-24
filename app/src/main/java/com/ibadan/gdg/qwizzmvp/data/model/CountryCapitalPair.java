package com.ibadan.gdg.qwizzmvp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.text.Normalizer;

/**
 * Created by Efe on 21/09/2016.
 */

public class CountryCapitalPair implements Parcelable {

    public static final Parcelable.Creator<CountryCapitalPair> CREATOR = new Parcelable.Creator<CountryCapitalPair>() {
        @Override
        public CountryCapitalPair createFromParcel(Parcel source) {
            return new CountryCapitalPair(source);
        }

        @Override
        public CountryCapitalPair[] newArray(int size) {
            return new CountryCapitalPair[size];
        }
    };

    private static final String SPECIALS = "[-_.,;:'\\s]+";

    @SerializedName("_id") private String id;
    private String country;
    private String capital;
    private String userCapital = null;

    public CountryCapitalPair() {
    }

    protected CountryCapitalPair(Parcel in) {
        this.id = in.readString();
        this.country = in.readString();
        this.capital = in.readString();
        this.userCapital = in.readString();
    }

    public static int checkAnswerCorrect(CountryCapitalPair current, Capital capital) {

        if (!current.getId().equals(capital.getId())) return 0;

        String normalized;

        // sanitize user answer
        normalized = normalize(current.getCapital());
        String correct = normalized.replaceAll(SPECIALS, " ");

        normalized = normalize(capital.getName());
        String answer = normalized.replace(SPECIALS, " ").trim();

        return answer.equalsIgnoreCase(correct) ? 1 : 0;
    }

    private static String normalize(String text) {
        return text == null ? null : Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    public static void storeUserCapital(CountryCapitalPair current, Capital capital) {
        current.userCapital = capital.getName();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getUserCapital() {
        return userCapital;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Country asCountry() {
        return new Country(id, country);
    }

    public Capital asCapital() {
        return new Capital(id, capital);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.country);
        dest.writeString(this.capital);
        dest.writeString(this.userCapital);
    }
}
