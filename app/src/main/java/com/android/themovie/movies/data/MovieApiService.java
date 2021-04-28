package com.android.themovie.movies.data;

import com.android.themovie.constants.AppConstants;
import com.android.themovie.movies.model.MoviesParentDo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {

    @GET(AppConstants.Api_Popular_Movies)
    public Call<MoviesParentDo> getPopularMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page);
}