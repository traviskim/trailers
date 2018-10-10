package com.metalpay.trailers.retrofit;

import com.metalpay.trailers.data.Genre;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TrailersService {
    @GET("movies/genres")
    Call<List<Genre>> getAllTrailers();
}
