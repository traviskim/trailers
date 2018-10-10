package com.metalpay.trailers.repository;

import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.metalpay.trailers.data.Profile;
import com.metalpay.trailers.retrofit.ProfileService;
import com.metalpay.trailers.retrofit.RetrofitClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class ProfileRepository {
    private Gson mGson = new Gson();

    private ProfileRepository(){ }

    public interface OnProfileRepositoryCompleted{
        void onGettingProfileCompleted(MutableLiveData<Profile> profile);
    }

    public static ProfileRepository getInstance(){
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper{
        private static final ProfileRepository INSTANCE = new ProfileRepository();
    }

    public void getProfile(final String baseUrl, final OnProfileRepositoryCompleted listener){
        final MutableLiveData<Profile> data = new MutableLiveData<>();
        new AsyncTask<Void, Void, Profile>(){
            @Override
            protected Profile doInBackground(Void... voids) {
                try{
                    ProfileService profileService = RetrofitClient.getInstance(baseUrl).create(ProfileService.class);
                    Call<Profile> call = profileService.getProfile();
                    Response<Profile> response = call.execute();
                    Profile profile = response.body();
                    return profile;
                }catch(IOException e){
                    System.out.print(e.getMessage());
                }
                return null;
            }

            @Override
            protected void onPostExecute(Profile profile) {
                if(profile != null){
                    data.setValue(profile);
                    listener.onGettingProfileCompleted(data);
                }
            }
        }.execute();
    }
}
