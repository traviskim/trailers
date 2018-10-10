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
import com.metalpay.trailers.adapter.CastAdapter;
import com.metalpay.trailers.data.Cast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CastListFragment extends Fragment implements CastAdapter.OnCastClickListener {
    private static final String LOG_TAG = CastListFragment.class.getSimpleName();
    private static final String ARG_CAST = "ARG_CAST";
    private OnFragmentInteractionListener mListener;

    @BindView(R.id.rv_list) RecyclerView mCastListReCylerView;
    @BindView(R.id.tv_title) TextView mCastTitleTextView;

    private List<Cast> mCastList = new ArrayList<>();
    private CastAdapter mCastAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Cast cast);
    }

    public CastListFragment() {
        // Required empty public constructor
    }

    public static CastListFragment newInstance(List<Cast> castList) {
        CastListFragment fragment = new CastListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_CAST, (ArrayList<Cast>)castList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCastList = getArguments().getParcelableArrayList(ARG_CAST);
        }
        mCastAdapter = new CastAdapter(mCastList, this);
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
        mCastListReCylerView.setAdapter(mCastAdapter);
        mCastListReCylerView.setLayoutManager(mLinearLayoutManager);
        mCastTitleTextView.setText(R.string.cast_and_crew);
        mCastTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13f);
    }

    @Override
    public void onCastClickListener(Cast cast) {
        if (mListener != null) {
            //Do nothing for cast click
            //mListener.onFragmentInteraction(cast);
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
