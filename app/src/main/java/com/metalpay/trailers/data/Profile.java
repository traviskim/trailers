package com.metalpay.trailers.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Profile implements Parcelable{
    @SerializedName("given_name")
    private String givenName;
    @SerializedName("family_name")
    private String familyName;
    @SerializedName("picture_url")
    private String pictureUrl;
    @SerializedName("location_enabled")
    private boolean locationEnabled;
    @SerializedName("suggestion_radius")
    private int suggestionRadius;

    public Profile(){}

    public Profile(Parcel in ){
        this.givenName = in.readString();
        this.familyName = in.readString();
        this.pictureUrl = in.readString();
        this.locationEnabled = in.readInt() != 0;
        this.suggestionRadius = in.readInt();
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel source) {
            return new Profile(source);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.givenName);
        dest.writeString(this.familyName);
        dest.writeString(this.pictureUrl);
        dest.writeInt(this.locationEnabled ? 1 : 0);
        dest.writeInt(this.suggestionRadius);
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public boolean isLocationEnabled() {
        return locationEnabled;
    }

    public void setLocationEnabled(boolean locationEnabled) {
        this.locationEnabled = locationEnabled;
    }

    public int getSuggestionRadius() {
        return suggestionRadius;
    }

    public void setSuggestionRadius(int suggestionRadius) {
        this.suggestionRadius = suggestionRadius;
    }
}
