package com.metalpay.trailers.ui;

import android.arch.lifecycle.MutableLiveData;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.metalpay.trailers.R;
import com.metalpay.trailers.data.Genre;
import com.metalpay.trailers.repository.MovieRepository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GenreListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.swipe_container) SwipeRefreshLayout mSwipeLayout;
    @BindView(R.id.container) LinearLayout mRootLayout;
    private MutableLiveData<List<Genre>> mGenreList;
    private MovieRepository movieRepository;
    public GenreListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieRepository = MovieRepository.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_genre_list, container, false);
        ButterKnife.bind(this, view);

        mSwipeLayout.setOnRefreshListener(this);
        if(savedInstanceState == null) {
            loadTrailers();
        }
        return view;
    }

    private void addGenreTrailers(MutableLiveData<List<Genre>> genreList){
        mGenreList = genreList;
        mRootLayout.removeAllViews();
        for(int i=0; i<mGenreList.getValue().size(); i++) {
            Genre genre = mGenreList.getValue().get(i);
            FrameLayout frameLayout = new FrameLayout(getContext());
            frameLayout.setId(View.generateViewId());
            if(i == mGenreList.getValue().size()-1){
                FrameLayout.LayoutParams flParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                flParams.bottomMargin = 150;
                frameLayout.setLayoutParams(flParams);
            }
            mRootLayout.addView(frameLayout);
            Fragment mMovieFragment = MovieListFragment.newInstance(genre);
            getChildFragmentManager().beginTransaction().replace(frameLayout.getId(), mMovieFragment, "").commit();
        }
        mSwipeLayout.setRefreshing(false);
    }

    private void loadTrailers(){
        movieRepository.getAllTrailers(getString(R.string.base_url_genres), new MovieRepository.OnMovieRepositoryCompleted() {
            @Override
            public void onGettingAllCompleted(MutableLiveData<List<Genre>> genreList) {
                addGenreTrailers(genreList);
            }
        });
    }

    @Override
    public void onRefresh() {
        loadTrailers();
    }

}
