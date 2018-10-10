package com.metalpay.trailers.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.metalpay.trailers.R;
import com.metalpay.trailers.adapter.MovieAdapter;
import com.metalpay.trailers.data.Genre;
import com.metalpay.trailers.data.Movie;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListFragment extends Fragment implements MovieAdapter.OnMovieClickListener {
    private static final String LOG_TAG = MovieListFragment.class.getSimpleName();
    private static final String ARG_GENRE = "ARG_GENRE";
    private Genre mParamGenre;
    private OnFragmentInteractionListener mListener;

    @BindView(R.id.rv_list) RecyclerView mMovieListReCylerView;
    @BindView(R.id.tv_title) TextView mGenreTitleTextView;

    private List<Movie> mMovieList = new ArrayList<>();
    private MovieAdapter mMovieAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Movie movie);
    }

    public MovieListFragment() {
        // Required empty public constructor
    }
    public static MovieListFragment newInstance(Genre genre) {
        MovieListFragment fragment = new MovieListFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_GENRE, genre);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParamGenre = getArguments().getParcelable(ARG_GENRE);
            mMovieList = mParamGenre.getMovieList();
        }
        mMovieAdapter = new MovieAdapter(mMovieList, this);
        mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMovieListReCylerView.setAdapter(mMovieAdapter);
        mMovieListReCylerView.setLayoutManager(mLinearLayoutManager);
        mGenreTitleTextView.setText(mParamGenre.getName().toUpperCase());
        mGenreTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f);
    }

    @Override
    public void onMovieClickListener(Movie movie) {
        if (mListener != null) {
            mListener.onFragmentInteraction(movie);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
