package com.metalpay.trailers.retrofit;

import com.metalpay.trailers.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;
    public static Retrofit getInstance(String baseUrl){
        if(retrofit == null){
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            if(BuildConfig.DEBUG) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                httpClient.addInterceptor(interceptor);
            }

            retrofit = new Retrofit.Builder().baseUrl(baseUrl).client(httpClient.build()).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}