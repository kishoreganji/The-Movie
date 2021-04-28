package com.android.themovie.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.themovie.R;
import com.android.themovie.constants.AppConstants;
import com.android.themovie.movies.model.MovieDo;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MovieGridAdapter extends RecyclerView.Adapter<MovieGridAdapter.ViewHolder> {

    private List<MovieDo> movieDos;
    private LayoutInflater mInflater;
    private Context context;

    public MovieGridAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context=context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.movie_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvMovieName.setText(movieDos.get(position).getTitle());
        Picasso.get().load(AppConstants.Img_Base_Url +movieDos.get(position).getPoster_path()).placeholder(R.drawable.movie_placeholder).into(holder.ivMovie);
        if(movieDos.get(position).isVideo()){
            holder.ivPlayMovie.setVisibility(View.VISIBLE);
            holder.ivPlayMovie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO play video
                }
            });
        }
        else {
            holder.ivPlayMovie.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return movieDos!=null?movieDos.size():0;
    }

    public void refreshAdapter(List<MovieDo> movieDos) {
        this.movieDos = movieDos;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivMovie, ivPlayMovie;
        private TextView tvMovieName;

        public ViewHolder(View itemView) {
            super(itemView);
            ivMovie         = itemView.findViewById(R.id.ivMovie);
            ivPlayMovie     = itemView.findViewById(R.id.ivPlayMovie);
            tvMovieName     = itemView.findViewById(R.id.tvMovieName);
        }
    }
}