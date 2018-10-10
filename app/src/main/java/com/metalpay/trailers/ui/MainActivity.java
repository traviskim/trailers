package com.metalpay.trailers.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.metalpay.trailers.R;
import com.metalpay.trailers.data.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.metalpay.trailers.ui.MovieDetailActivity.MOVIE_DETAIL_EXTRA_KEY;

public class MainActivity extends AppCompatActivity implements MovieListFragment.OnFragmentInteractionListener {
    private static final String SELECTED_NAV_ITEM_STATE_KEY = "SELECTED_NAV_ITEM_STATE_KEY";
    @BindView(R.id.navigation_view_container) FrameLayout mRootLayout;
    @BindView(R.id.bottom_navigation) BottomNavigationView mNavigation;
//    @BindView(R.id.tl_sliding) TabLayout mSlidingTabLayout;
//    @BindView(R.id.vp_viewPager) ViewPager mViewPager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_trailers:
                    Fragment genreListFragment = new GenreListFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.navigation_view_container, genreListFragment, "").commit();
                    return true;
                case R.id.navigation_profile:
                    Fragment profileFragment = new ProfileFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.navigation_view_container, profileFragment, "").commit();
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Fragment genreListFragment = new GenreListFragment();
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.navigation_view_container, genreListFragment, "").commit();
        }else{
            if(R.id.navigation_trailers == savedInstanceState.getInt(SELECTED_NAV_ITEM_STATE_KEY)){
                getSupportFragmentManager().beginTransaction().replace(R.id.navigation_view_container, genreListFragment, "").commit();
            }
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECTED_NAV_ITEM_STATE_KEY, mNavigation.getSelectedItemId());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onFragmentInteraction(Movie movie) {
        //Handle Movie click to open movie detail
        Intent i = new Intent(MainActivity.this, MovieDetailActivity.class);
        i.putExtra(MOVIE_DETAIL_EXTRA_KEY, movie);
        startActivity(i);
    }
}
