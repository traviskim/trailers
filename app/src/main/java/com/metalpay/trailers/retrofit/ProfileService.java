package com.metalpay.trailers.retrofit;

import com.metalpay.trailers.data.Profile;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProfileService {
    @GET("users/me")
    Call<Profile> getProfile();
}
