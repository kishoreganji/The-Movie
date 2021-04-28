package com.android.themovie.movies.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MoviesParentDo implements Serializable {

    public List<MovieDo> getResults() { return results; }

    public void setResults(List<MovieDo> results) {
        this.results = results;
    }
    
    @SerializedName("page")
    public int page;
    @SerializedName("total_pages")
    public String total_pages;
    @SerializedName("total_results")
    public String total_results;
    @SerializedName("results")
    public List<MovieDo> results;
}
