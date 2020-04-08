package com.exmaple.android.popularmovies.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.exmaple.android.popularmovies.DetailsActivity;
import com.exmaple.android.popularmovies.R;
import com.exmaple.android.popularmovies.data.Movie;
import com.squareup.picasso.Picasso;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private Movie[] movies;

    public RecyclerViewAdapter(Context context,  Movie[] images) {
        this.context = context;
        this.movies = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_list, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, final int i) {
        String image_path = "http://image.tmdb.org/t/p/w185/";
        image_path = image_path + movies[i].getPoster_path();
        Picasso.get().load(image_path).into(viewHolder.image);
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movie movie = movies[i];
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
        });
    }

    @Override
    public int getItemCount() {
        return movies.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //This part for Listener
        LinearLayout linearLayout;

        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_image);

            //This part for Listener
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}
