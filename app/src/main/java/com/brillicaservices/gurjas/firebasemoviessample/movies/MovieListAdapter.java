package com.brillicaservices.gurjas.firebasemoviessample.movies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brillicaservices.gurjas.firebasemoviessample.R;

import java.util.ArrayList;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    private ListItemClickListener itemClickListener;
    private ArrayList<MoviesModelView> studentArrayList;

    public MovieListAdapter(ArrayList<MoviesModelView> studentArrayList) {
        this.studentArrayList = studentArrayList;
    }

    public MovieListAdapter(ArrayList<MoviesModelView> studentArrayList, ListItemClickListener itemClickListener) {
        this.studentArrayList = studentArrayList;
        this.itemClickListener= itemClickListener;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View itemView = layoutInflater.inflate(R.layout.recycler_list_item, parent, false);

        MovieViewHolder movieViewHolder = new MovieViewHolder(itemView);

        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(MovieListAdapter.MovieViewHolder holder, int position) {
        MoviesModelView moviesModelView = studentArrayList.get(position);

        holder.movieThumbnail.setImageResource(moviesModelView.image);
        holder.movieName.setText(moviesModelView.title);
        holder.movieDescription.setText(moviesModelView.description);
        holder.rating.setText("" + moviesModelView.rating + "/5");
    }

    @Override
    public int getItemCount() {
        return studentArrayList.size();
    }

    public interface ListItemClickListener {
        void onListItemClickListener(int clickedItemIndex);
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView movieThumbnail;
        TextView movieName, movieDescription, rating;

        public MovieViewHolder(View itemView) {
            super(itemView);

            movieThumbnail = itemView.findViewById(R.id.movie_thumbnail);
            movieName = itemView.findViewById(R.id.movie_name_title);
            movieDescription = itemView.findViewById(R.id.movie_description);
            rating = itemView.findViewById(R.id.movie_rating);
        }


    public void onClick(View v) {
        int clickedPosition = getAdapterPosition();
        itemClickListener.onListItemClickListener(clickedPosition);
    }
    }
}








