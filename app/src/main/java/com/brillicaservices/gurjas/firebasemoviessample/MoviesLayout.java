package com.brillicaservices.gurjas.firebasemoviessample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.brillicaservices.gurjas.firebasemoviessample.database.DatabaseHelper;
import com.brillicaservices.gurjas.firebasemoviessample.movies.MovieListAdapter;
import com.brillicaservices.gurjas.firebasemoviessample.movies.MoviesModelView;
import com.brillicaservices.gurjas.firebasemoviessample.series.SeriesListAdapter;
import com.brillicaservices.gurjas.firebasemoviessample.series.SeriesModelView;

import java.util.ArrayList;

public class MoviesLayout extends AppCompatActivity  implements MovieListAdapter.ListItemClickListener {

    DatabaseHelper db;
    RecyclerView rv;
    MovieListAdapter recyclerAdapter;
    ArrayList<MoviesModelView> studentArrayList = new ArrayList<MoviesModelView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies_layout);

        rv = findViewById(R.id.recycler_view_movies);
        db = new DatabaseHelper(this);

        studentArrayList.addAll(db.allMovieDetails());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);

        rv.setHasFixedSize(true);
        recyclerAdapter = new MovieListAdapter(studentArrayList,this);
        rv.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();

    }

    @Override
    public void onListItemClickListener(int clickedItemIndex) {
        Toast.makeText(getApplicationContext(),studentArrayList.get(clickedItemIndex).title,Toast.LENGTH_LONG).show();
    }
}
