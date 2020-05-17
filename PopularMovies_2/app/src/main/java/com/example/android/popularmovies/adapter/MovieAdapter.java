package com.example.android.popularmovies.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.popularmovies.activity.DetailsActivity;
import com.example.android.popularmovies.R;
import com.example.android.popularmovies.data.Movie;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private static final String TAG = "MovieAdapter";

    final private Movie[] movies;

    public MovieAdapter(Movie[] movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.movie_item_list, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder viewHolder, final int i) {

        String image_path = viewHolder.image.getContext().getString(R.string.image_path);
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
        @BindView(R.id.iv_image) ImageView image;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Context context = view.getContext();

            Movie movie = movies[adapterPosition];
            
            String id = movie.getId();
            String title = movie.getTitle();
            String release_date = movie.getRelease_date();
            String backdrop_path = movie.getBackdrop_path();
            String poster_path = movie.getPoster_path();
            String year = (release_date.split(context.getString(R.string.delimiter)))[0];
            String vote_average = movie.getVote_average();
            String overView = movie.getOverview();
            String[] urlForTrailers = movie.getUrlForTrailers();

            Intent intent = new Intent(view.getContext(), DetailsActivity.class);

            intent.putExtra(context.getString(R.string.id), id);
            intent.putExtra(context.getString(R.string.title), title);
            intent.putExtra(context.getString(R.string.release_date), year);
            intent.putExtra(context.getString(R.string.backdrop_path), backdrop_path);
            intent.putExtra(context.getString(R.string.poster_path), poster_path);
            intent.putExtra(context.getString(R.string.vote_average), vote_average);
            intent.putExtra(context.getString(R.string.over_view), overView);
            intent.putExtra(context.getString(R.string.listOfTrailers), urlForTrailers);

            view.getContext().startActivity(intent);
        }
    }
}
