package com.metalpay.trailers.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.metalpay.trailers.R;
import com.metalpay.trailers.adapter.CastAdapter;
import com.metalpay.trailers.data.Cast;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CastViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.iv_cast_profile_image)
    ImageView mProfileImageView;
    @BindView(R.id.tv_cast_name)
    TextView mCastNameTextView;

    public CastViewHolder(View itemView){
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final Cast cast, final CastAdapter.OnCastClickListener listener){
        Picasso.get().load(cast.getPictureUrl()).into(mProfileImageView);
        mCastNameTextView.setText(String.format(Locale.US, "%s %s", cast.getGivenName().toUpperCase(), cast.getFamilyName().toUpperCase()));

        //No Click event is set for a cast
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onCastClickListener(cast);
//            }
//        });
    }
}
