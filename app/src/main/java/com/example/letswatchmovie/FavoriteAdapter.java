package com.example.letswatchmovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyFavViewHolder> {

    private Context mContext;
    private List<FavoriteList> mFavouriteMovies;

    public FavoriteAdapter (Context context , List<FavoriteList> FavouriteData){
        this.mContext = context;
        this.mFavouriteMovies = FavouriteData;
    }

    @Override
    public MyFavViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View movieList = LayoutInflater.from(mContext)
                .inflate(R.layout.movie_item , parent , false);

        return new MyFavViewHolder(movieList);
    }

    @Override
    public void onBindViewHolder(MyFavViewHolder holder, int position) {
        holder.movieId.setText(mFavouriteMovies.get(position).getMovieId());
        holder.movieTitle.setText(mFavouriteMovies.get(position).getMovieTitle());
        holder.relDate.setText(mFavouriteMovies.get(position).getMovieRelDate());

        Glide.with(mContext).load("https://image.tmdb.org/t/p/original"+mFavouriteMovies.get(position).getMoviePoster()).into(holder.movieImg);

    }

    @Override
    public int getItemCount() {
        return mFavouriteMovies.size();
    }

    public class MyFavViewHolder extends RecyclerView.ViewHolder{

        TextView movieTitle;
        TextView movieId;
        ImageView movieImg;
        TextView relDate;

        public MyFavViewHolder(View itemView) {
            super(itemView);

            movieId = itemView.findViewById(R.id.idView);
            movieImg = itemView.findViewById(R.id.moviePoster);
            movieTitle = itemView.findViewById(R.id.titleView);
            relDate = itemView.findViewById(R.id.dateView);
        }
    }
}
