package com.metalpay.trailers.ui;

import android.arch.lifecycle.MutableLiveData;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.metalpay.trailers.R;
import com.metalpay.trailers.data.Profile;
import com.metalpay.trailers.repository.ProfileRepository;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment {
    private ProfileRepository profileRepository;

    @BindView(R.id.iv_picture) ImageView mPictureImageView;
    @BindView(R.id.tv_name) TextView mNameTextview;
    @BindView(R.id.swt_location) Switch mLocationSwitch;
    @BindView(R.id.sb_radius) SeekBar mRadiusSeekBar;
    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileRepository = ProfileRepository.getInstance();
    }

    private void loadValuesForViews(MutableLiveData<Profile> profile){
        Picasso.get().load(profile.getValue().getPictureUrl()).into(mPictureImageView);
        mNameTextview.setText(String.format(Locale.getDefault(), "%s %s", profile.getValue().getGivenName().toUpperCase(), profile.getValue().getFamilyName().toUpperCase()));
        mLocationSwitch.setChecked(profile.getValue().isLocationEnabled());
        mRadiusSeekBar.setProgress(profile.getValue().getSuggestionRadius());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        profileRepository.getProfile(getString(R.string.base_url_profile), new ProfileRepository.OnProfileRepositoryCompleted() {
            @Override
            public void onGettingProfileCompleted(MutableLiveData<Profile> profile) {
                loadValuesForViews(profile);
            }
        });
        return view;
    }
}
