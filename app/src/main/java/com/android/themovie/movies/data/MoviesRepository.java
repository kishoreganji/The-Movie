package com.android.themovie.movies.data;

import com.android.themovie.movies.model.MoviesParentDo;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepository {

    private static MoviesRepository repository;
    private MovieApiService movieApiService;

    private MoviesRepository() {
        movieApiService = RetroInstance.getRetroInstance().create(MovieApiService.class);
    }

    public static MoviesRepository getInstance() {
        if(repository == null){
            repository = new MoviesRepository();
        }
        return repository;
    }

    public MutableLiveData<MoviesParentDo> getMoviesParentDo(String api_Key, String language, int page){
        final MutableLiveData<MoviesParentDo> moviesData = new MutableLiveData<>();
        movieApiService.getPopularMovies(api_Key, language, page).enqueue(new Callback<MoviesParentDo>() {
            @Override
            public void onResponse(Call<MoviesParentDo> call, Response<MoviesParentDo> response) {
                if (response.isSuccessful()){
                    moviesData.setValue(response.body());
                }
                else {
                    moviesData.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<MoviesParentDo> call, Throwable throwable) {
                moviesData.setValue(null);
            }
        });
        return moviesData;
    }

}
