package com.metalpay.trailers.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.metalpay.trailers.R;
import com.metalpay.trailers.data.Cast;
import com.metalpay.trailers.data.Movie;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.metalpay.trailers.ui.WebViewActivity.TITLE_EXTRA_KEY;
import static com.metalpay.trailers.ui.WebViewActivity.URL_EXTRA_KEY;

public class MovieDetailActivity extends AppCompatActivity implements CastListFragment.OnFragmentInteractionListener {
    public static final String MOVIE_DETAIL_EXTRA_KEY = "MOVIE_DETAIL_EXTRA_KEY";
    private Movie mMovie;
    private String mShareMsg;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.tv_imdb_rating) TextView mIMDBRatingTextView;
    @BindView(R.id.tv_rt_rating) TextView mRtRatingTextView;
    @BindView(R.id.tv_synopsis) TextView mSysnopsisTextView;
    @BindView(R.id.iv_movie_detail_poster) ImageView mMoviePosterImageView;
    @BindView(R.id.toolbar_title) TextView mToolbarTitle;
    @BindView(R.id.tb_favorite) ToggleButton mFavToggle;
    @BindView(R.id.iv_source) ImageView mSourceImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if(getIntent().getExtras() != null){
            mMovie = getIntent().getParcelableExtra(MOVIE_DETAIL_EXTRA_KEY);

            mToolbarTitle.setText(mMovie.getTitle().toUpperCase());
            Picasso.get().load(mMovie.getThumbnailUrl()).into(mMoviePosterImageView);
            mIMDBRatingTextView.setText(String.format(Locale.getDefault(),"%.1f", mMovie.getImdbScore()));
            mRtRatingTextView.setText(String.format(Locale.getDefault(), "%d", mMovie.getRtScore()));
            mSysnopsisTextView.setText(mMovie.getSynopsis());
            mShareMsg = String.format(Locale.getDefault(), "Hey check out %s! It has a %.1f on IMDB and stars %s %s", mMovie.getTitle(), mMovie.getImdbScore(), mMovie.getCastList().get(0).getGivenName(), mMovie.getCastList().get(0).getFamilyName());

            //Add Cast list
            Fragment castListFragment = CastListFragment.newInstance(mMovie.getCastList());
            getSupportFragmentManager().beginTransaction().replace(R.id.cast_container, castListFragment, "").commit();
            mFavToggle.setChecked(mMovie.isLiked());
            mFavToggle.setClickable(false);
            mSourceImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MovieDetailActivity.this, WebViewActivity.class);
                    i.putExtra(URL_EXTRA_KEY, mMovie.getSourceUrl());
                    i.putExtra(TITLE_EXTRA_KEY, mMovie.getTitle());
                    startActivity(i);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.share:
                sendShareIntentMsg();
        }
        return super.onOptionsItemSelected(item);
    }

    private void sendShareIntentMsg(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, mShareMsg);
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Share selected movie"));
    }

    @Override
    public void onFragmentInteraction(Cast cast) {

    }
}
