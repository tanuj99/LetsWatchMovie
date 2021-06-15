package com.example.letswatchmovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> implements Filterable {

    private Context mContext;
    private List<Movie> mData;
    private List<Movie> allMovies;

    public MovieAdapter(Context context , List<Movie> data ){
        this.mContext = context;
        this.mData = data;
        this.allMovies = new ArrayList<>(mData);
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView movieTitle;
        TextView movieId;
        ImageView movieImg;
        TextView relDate;
        ImageView favBtn;

        public MyViewHolder(View itemView) {
            super(itemView);

            movieId = itemView.findViewById(R.id.idView);
            movieImg = itemView.findViewById(R.id.moviePoster);
            movieTitle = itemView.findViewById(R.id.titleView);
            relDate = itemView.findViewById(R.id.dateView);
            favBtn = itemView.findViewById(R.id.favIcon);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View movieList = LayoutInflater.from(mContext)
                .inflate(R.layout.movie_item , parent , false);

        MyViewHolder viewHolder = new MyViewHolder(movieList);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Movie movieList = mData.get(position);

        holder.movieId.setText(movieList.getmVote());
        holder.movieTitle.setText(movieList.getmTitle());
        holder.relDate.setText(movieList.getmRelDate());

        Glide.with(mContext).load("https://image.tmdb.org/t/p/original" + movieList.getmPosterId()).into(holder.movieImg);

        if(MainActivity.favoriteDatabase.favoriteDao().isFavorite(movieList.getmVote())==1)
            holder.favBtn.setImageResource(R.drawable.ic_baseline_favorite_24);
        else
            holder.favBtn.setImageResource(R.drawable.ic_baseline_favorite_border_24);

        holder.favBtn.setOnClickListener(v -> {
            FavoriteList favoriteList = new FavoriteList();

            String movieId = movieList.getmVote();
            String moviePoster = movieList.getmPosterId();
            String movieTitle = movieList.getmTitle();
            String movieRelDate = movieList.getmRelDate();

            favoriteList.setMovieId(movieId);
            favoriteList.setMoviePoster(moviePoster);
            favoriteList.setMovieRelDate(movieRelDate);
            favoriteList.setMovieTitle(movieTitle);

            if (MainActivity.favoriteDatabase.favoriteDao().isFavorite(movieId)!=1){
                holder.favBtn.setImageResource(R.drawable.ic_baseline_favorite_24);
                MainActivity.favoriteDatabase.favoriteDao().addData(favoriteList);
            }
            else{
                holder.favBtn.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                MainActivity.favoriteDatabase.favoriteDao().delete(favoriteList);
            }

        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            
            List<Movie> filterList = new ArrayList<>();

            if (constraint.toString().isEmpty()){
                filterList.addAll(allMovies);
            }else{

                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Movie movie : allMovies){
                    if (movie.getmTitle().toLowerCase().contains(filterPattern)){
                        filterList.add(movie);
                    }

                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filterList;
            filterResults.count = filterList.size();

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            mData.clear();
            mData.addAll((Collection<? extends Movie>) results.values);
            notifyDataSetChanged();

        }
    };
}
