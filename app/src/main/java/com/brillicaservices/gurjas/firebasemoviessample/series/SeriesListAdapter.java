package com.brillicaservices.gurjas.firebasemoviessample.series;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.brillicaservices.gurjas.firebasemoviessample.R;
import com.brillicaservices.gurjas.firebasemoviessample.SeriesLayout;
import com.brillicaservices.gurjas.firebasemoviessample.movies.MovieListAdapter;
import com.brillicaservices.gurjas.firebasemoviessample.movies.MoviesModelView;


import java.util.ArrayList;

public class SeriesListAdapter extends RecyclerView.Adapter<SeriesListAdapter.SeriesViewHolder> {

    private SeriesListAdapter.ListItemClickListener itemClickListener;
    private ArrayList<MoviesModelView> studentArrayList;

    ArrayList<SeriesModelView> seriesModelViewArrayList = new ArrayList<>();

    public SeriesListAdapter(ArrayList<SeriesModelView> seriesModelViewArrayList) {
        this.seriesModelViewArrayList = seriesModelViewArrayList;
    }

    public SeriesListAdapter(ArrayList<MoviesModelView> studentArrayList, ListItemClickListener itemClickListener) {
        this.studentArrayList = studentArrayList;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public SeriesListAdapter.SeriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View itemView = layoutInflater.inflate(R.layout.recycler_list_item1, parent, false);

        SeriesListAdapter.SeriesViewHolder SeriesViewHolder = new SeriesListAdapter.SeriesViewHolder(itemView);

        return SeriesViewHolder;
    }

    @Override
    public void onBindViewHolder(SeriesListAdapter.SeriesViewHolder holder, int position) {
        SeriesModelView seriesModelView = seriesModelViewArrayList.get(position);

        holder.seriesThumbnail.setImageResource(seriesModelView.image);
        holder.seriesName.setText(seriesModelView.name);
        holder.seriesDescription.setText(seriesModelView.desc);
        holder.rating.setText("" + seriesModelView.Rating + "/5");
    }

    @Override
    public int getItemCount() {
        return seriesModelViewArrayList.size();
    }

    public interface ListItemClickListener {
        void onListItemClickListener(int clickedItemIndex);
    }

    public class SeriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView seriesThumbnail;
        TextView seriesName, seriesDescription, rating;

        public SeriesViewHolder(View itemView) {
            super(itemView);

            seriesThumbnail = itemView.findViewById(R.id.movie_thumbnail1);
            seriesName = itemView.findViewById(R.id.movie_name_title1);
            seriesDescription = itemView.findViewById(R.id.movie_description1);
            rating = itemView.findViewById(R.id.movie_rating1);


        }

        @Override
        public void onClick(View v) {
            int clickedposition=getAdapterPosition();
            itemClickListener.onListItemClickListener(clickedposition);

        }
    }


    }