package com.metalpay.trailers.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.metalpay.trailers.R;
import com.metalpay.trailers.adapter.MovieAdapter;
import com.metalpay.trailers.data.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.iv_movie_poster)
    ImageView mMoviePosterImageView;
    @BindView(R.id.tv_movie_title)
    TextView mMovieTitleTextView;

    public MovieViewHolder(View itemView){
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final Movie movie, final MovieAdapter.OnMovieClickListener listener){
        Picasso.get().load(movie.getThumbnailUrl()).into(mMoviePosterImageView);
        mMovieTitleTextView.setText(movie.getTitle().toUpperCase());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMovieClickListener(movie);
            }
        });
    }
}
