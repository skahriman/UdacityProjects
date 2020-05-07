package com.example.android.popularmovies.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.popularmovies.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {

    private static final String TAG = "TrailerAdapter";
    final private String[] trailers;

    public TrailerAdapter(String[] trailers) {
        this.trailers = trailers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.trailer_item_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.trailer.setText("Trailer " + (i+1));
    }

    @Override
    public int getItemCount() {
        return trailers.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.btn_play) Button playButton;
        @BindView(R.id.tv_trailer)
        TextView trailer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            playButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = ((Activity) view.getContext()).getIntent();
            String[] listOfUrls = intent.getStringArrayExtra("listOfUrls");
            String url = listOfUrls[getAdapterPosition()];

            Intent trailerIntent = new Intent(Intent.ACTION_VIEW);
            trailerIntent.setData(Uri.parse(url));

            view.getContext().startActivity(trailerIntent);
        }
    }

}
