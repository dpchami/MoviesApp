package hosanna.com.moviesclub.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import hosanna.com.moviesclub.R;
import hosanna.com.moviesclub.data.models.Movie;
import hosanna.com.moviesclub.util.Constants;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{


    private List<Movie> mMovieList;

    public MovieAdapter() {
        mMovieList = new ArrayList<Movie>();
    }

    public void setMovieList(List<Movie> movieList) {
        mMovieList = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_row,parent,false);
      return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        Movie movie = mMovieList.get(position);
        holder.setDetails(movie);

    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        TextView title,release_year;
        ImageView posterImg;
        String url;

        public MovieViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            release_year = view.findViewById(R.id.release_year);
            posterImg = view.findViewById(R.id.poster);

        }

        @SuppressLint("SetTextI18n")
        public void setDetails(Movie movie){
            title.setText(movie.getTitle());
            release_year.setText("("+movie.getReleaseDate()+")");
            url = movie.getPosterUrl();

            Log.d("IMAGE_PATH", url);
            Glide.with(itemView.getContext())
                    .load(url)
                    .into(posterImg);
        }
    }
}
