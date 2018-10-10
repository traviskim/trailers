package com.metalpay.trailers.repository;

import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.metalpay.trailers.data.Genre;
import com.metalpay.trailers.retrofit.RetrofitClient;
import com.metalpay.trailers.retrofit.TrailersService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MovieRepository{
    private Gson mGson = new Gson();

    private MovieRepository(){ }

    public interface OnMovieRepositoryCompleted{
        void onGettingAllCompleted(MutableLiveData<List<Genre>> genreList);
    }

    public static MovieRepository getInstance(){
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper{
        private static final MovieRepository INSTANCE = new MovieRepository();
    }

    public void getAllTrailers(final String baseUrl, final OnMovieRepositoryCompleted listener){
        final MutableLiveData<List<Genre>> data = new MutableLiveData<>();
        new AsyncTask<Void, Void, List<Genre>>(){
            @Override
            protected List<Genre> doInBackground(Void... voids) {
                try{
                    TrailersService trailersService = RetrofitClient.getInstance(baseUrl).create(TrailersService.class);
                    Call<List<Genre>> call = trailersService.getAllTrailers();
                    Response<List<Genre>> response = call.execute();
                    List<Genre> genreList = response.body();
                    return genreList;
                }catch(IOException e){
                    System.out.print(e.getMessage());
                }
                return null;
            }

            @Override
            protected void onPostExecute(List<Genre> genres) {
                if(genres != null){
                    data.setValue(genres);
                    listener.onGettingAllCompleted(data);
                }
            }
        }.execute();
    }

    //Test purpose
    public List<Genre> getAllTrailersFromFile(InputStream filtInputStream){
        final MutableLiveData<List<Genre>> data = new MutableLiveData<>();
            Genre[] genreList = null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(filtInputStream));
            genreList = mGson.fromJson(reader, Genre[].class);
            return Arrays.asList(genreList);
    }
}
