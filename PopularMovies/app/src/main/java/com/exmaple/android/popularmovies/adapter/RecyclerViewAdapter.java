package com.exmaple.android.popularmovies.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.exmaple.android.popularmovies.DetailsActivity;
import com.exmaple.android.popularmovies.R;
import com.exmaple.android.popularmovies.data.Movie;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private Movie[] movies;

    public RecyclerViewAdapter(Movie[] images) {
        this.movies = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_list, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, final int i) {

        String image_path = "http://image.tmdb.org/t/p/w185/";
        image_path = image_path + movies[i].getPoster_path();
        Picasso.get()
                .load(image_path)
                .fit()
                .into(viewHolder.image, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.d(TAG, "onSuccess: loaded successfully" );
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.d(TAG, "onError: failed on loading image");
                    }
                });
    }

    @Override
    public int getItemCount() {
        return movies.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Movie movie = movies[adapterPosition];
            String title = movie.getTitle();
            String poster_path = movie.getPoster_path();
            String release_date = movie.getRelease_date();
            String year = (release_date.split("-"))[0];
            String vote_average = movie.getVote_average();
            String overView = movie.getOverview();

            Intent intent = new Intent(view.getContext(), DetailsActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("poster_path", poster_path);
            intent.putExtra("release_date", year);
            intent.putExtra("vote_average", vote_average);
            intent.putExtra("overView", overView);
            view.getContext().startActivity(intent);
        }
    }
}
