package com.android.themovie.movies.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.themovie.R;
import com.android.themovie.adapters.MovieGridAdapter;
import com.android.themovie.constants.AppConstants;
import com.android.themovie.movies.model.MovieDo;
import com.android.themovie.movies.model.MoviesParentDo;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MovieListActivity extends AppCompatActivity {

    private MoviesViewModel moviesViewModel;
    private RecyclerView rvPopularMovies;
    private MovieGridAdapter movieGridAdapter;
    private ProgressBar pbMovies;
    private TextView tvNoMovies;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies_list_layout);
        AppConstants.setCustomActionbar(MovieListActivity.this, "Popular Movies");
        initialiseControls();
        pbMovies.setVisibility(View.VISIBLE);
        tvNoMovies.setVisibility(View.GONE);
        rvPopularMovies.setVisibility(View.GONE);
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);

        if(AppConstants.isNetworkAvailable(MovieListActivity.this)){
            moviesViewModel.callPopularMovieAPI();
        }
        else {
            Toast.makeText(this, "Internet is not available!", Toast.LENGTH_SHORT).show();
        }
        moviesViewModel.getMoviesRepository().observe(MovieListActivity.this, new Observer<MoviesParentDo>() {
            @Override
            public void onChanged(MoviesParentDo moviesParentDo) {
                pbMovies.setVisibility(View.GONE);
                if(moviesParentDo != null){
                    List<MovieDo> movieDos = moviesParentDo.getResults();
                    refreshMoviesAdapter(movieDos);
                }
                else {
                    tvNoMovies.setVisibility(View.VISIBLE);
                    rvPopularMovies.setVisibility(View.GONE);
                    Toast.makeText(MovieListActivity.this, "Unable to fetch the data!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initialiseControls() {
        rvPopularMovies             = findViewById(R.id.rvPopularMovies);
        pbMovies                    = findViewById(R.id.pbMovies);
        tvNoMovies                  = findViewById(R.id.tvNoMovies);
        rvPopularMovies.setLayoutManager(new GridLayoutManager(MovieListActivity.this, 2, GridLayoutManager.VERTICAL, false));
        movieGridAdapter            = new MovieGridAdapter(MovieListActivity.this);
        rvPopularMovies.setAdapter(movieGridAdapter);
        rvPopularMovies.setItemAnimator(new DefaultItemAnimator());
        rvPopularMovies.setNestedScrollingEnabled(true);
    }
    private void failedFetchMovies(Integer error) {
        Toast.makeText(this, "Failed to Fetch movies : "+error, Toast.LENGTH_SHORT).show();
    }

    private void refreshMoviesAdapter(List<MovieDo> movieDos) {
        if(movieDos != null && movieDos.size() > 0) {
            tvNoMovies.setVisibility(View.GONE);
            rvPopularMovies.setVisibility(View.VISIBLE);
            movieGridAdapter.refreshAdapter(movieDos);
        }
        else {
            tvNoMovies.setVisibility(View.VISIBLE);
            rvPopularMovies.setVisibility(View.GONE);
        }
    }
}
