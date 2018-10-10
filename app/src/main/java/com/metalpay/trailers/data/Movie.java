package com.metalpay.trailers.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Movie implements Parcelable {
    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("thumbnail_url")
    private String thumbnailUrl;
    @SerializedName("imdb_score")
    private Double imdbScore;
    @SerializedName("rt_score")
    private Integer rtScore;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("liked")
    private boolean liked;
    @SerializedName("source_url")
    private String sourceUrl;
    @SerializedName("synopsis")
    private String synopsis;
    @SerializedName("cast")
    private List<Cast> castList;

    public Movie(){}

    public Movie(Parcel in){
        this.id = in.readString();
        this.title = in.readString();
        this.thumbnailUrl = in.readString();
        this.imdbScore = in.readDouble();
        this.rtScore = in.readInt();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
        this.liked = in.readInt() != 0;
        this.sourceUrl = in.readString();
        this.synopsis = in.readString();
        this.castList = new ArrayList<>();
        in.readList(this.castList, Cast.class.getClassLoader());
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.thumbnailUrl);
        dest.writeDouble(this.imdbScore);
        dest.writeInt(this.rtScore);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
        dest.writeInt(this.liked ? 1 : 0);
        dest.writeString(this.sourceUrl);
        dest.writeString(this.synopsis);
        dest.writeList(this.castList);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Double getImdbScore() {
        return imdbScore;
    }

    public void setImdbScore(Double imdbScore) {
        this.imdbScore = imdbScore;
    }

    public Integer getRtScore() {
        return rtScore;
    }

    public void setRtScore(Integer rtScore) {
        this.rtScore = rtScore;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public List<Cast> getCastList() {
        return castList;
    }

    public void setCastList(List<Cast> castList) {
        this.castList = castList;
    }
}
