package com.metalpay.trailers.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.metalpay.trailers.R;
import com.metalpay.trailers.data.Cast;
import com.metalpay.trailers.viewholder.CastViewHolder;
import com.metalpay.trailers.viewholder.MovieViewHolder;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastViewHolder> {
    private List<Cast> mCastList;
    private OnCastClickListener listener;
    public CastAdapter(List<Cast> castList, OnCastClickListener onCastClickListener){
        this.mCastList = castList;
        this.listener = onCastClickListener;
    }

    public interface OnCastClickListener{
        void onCastClickListener(Cast cast);
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cast, viewGroup, false);
        return new CastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder castViewHolder, int i) {
        castViewHolder.bind(mCastList.get(i), listener);
    }

    @Override
    public int getItemCount() {
        return mCastList.size();
    }

    public void setCastList(List<Cast> castList){
        this.mCastList = castList;
        notifyDataSetChanged();
    }

    public void clearCastList(){
        this.mCastList.clear();
        notifyDataSetChanged();
    }
}
