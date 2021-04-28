package com.android.themovie.movies.ui;

import com.android.themovie.constants.AppConstants;
import com.android.themovie.movies.data.MoviesRepository;
import com.android.themovie.movies.model.MoviesParentDo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MoviesViewModel extends ViewModel {

    private MutableLiveData<MoviesParentDo> mutableLiveData;
    private MoviesRepository moviesRepository;

    public MoviesViewModel() {

    }

    public void callPopularMovieAPI(){
        if (mutableLiveData != null){
            return;
        }
        moviesRepository = MoviesRepository.getInstance();
        mutableLiveData = moviesRepository.getMoviesParentDo(AppConstants.API_Key, "en-US", 1);
    }

    public LiveData<MoviesParentDo> getMoviesRepository() {
        return mutableLiveData;
    }
}
