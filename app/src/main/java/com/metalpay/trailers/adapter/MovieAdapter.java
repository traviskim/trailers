package com.metalpay.trailers.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.metalpay.trailers.R;
import com.metalpay.trailers.data.Movie;
import com.metalpay.trailers.viewholder.MovieViewHolder;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    private List<Movie> mMovieList;
    private OnMovieClickListener listener;
    public MovieAdapter(List<Movie> movieList, OnMovieClickListener onMovieClickListener){
        this.mMovieList = movieList;
        this.listener = onMovieClickListener;
    }

    public interface OnMovieClickListener{
        void onMovieClickListener(Movie movie);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        movieViewHolder.bind(mMovieList.get(i), listener);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public void setMovieList(List<Movie> movieList){
        this.mMovieList = mMovieList;
        notifyDataSetChanged();
    }

    public void clearMovieList(){
        this.mMovieList.clear();
        notifyDataSetChanged();
    }
}
