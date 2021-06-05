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

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private Context mContext;
    private List<Movie> mData;

    public MovieAdapter(Context context , List<Movie> data){
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View movieList = LayoutInflater.from(mContext)
                .inflate(R.layout.movie_item , parent , false);

        return new MyViewHolder(movieList);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        
        holder.movieId.setText(mData.get(position).getmVote());
        holder.movieTitle.setText(mData.get(position).getmTitle());

        Glide.with(mContext).load("https://image.tmdb.org/t/p/w500"+mData.get(position).getmPosterId()).into(holder.movieImg);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView movieTitle;
        TextView movieId;
        ImageView movieImg;

        public MyViewHolder(View itemView) {
            super(itemView);

            movieId = itemView.findViewById(R.id.idView);
            movieImg = itemView.findViewById(R.id.moviePoster);
            movieTitle = itemView.findViewById(R.id.titleView);
        }
    }
}
