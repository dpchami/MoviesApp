package hosanna.com.moviesclub.data;

import android.app.Application;
import android.arch.persistence.room.EmptyResultSetException;
import android.content.Context;

import java.util.List;

import hosanna.com.moviesclub.data.local.MovieDao;
import hosanna.com.moviesclub.data.models.Movie;
import hosanna.com.moviesclub.data.remote.MovieApiService;
import io.reactivex.Single;

public class MovieRepository {

    private final MovieDao movieDao;
    private final MovieApiService movieApiService;

    public MovieRepository(MovieDao movieDao, MovieApiService movieApiService) {
        this.movieDao = movieDao;
        this.movieApiService = movieApiService;
    }

    public Single<List<Movie>> getMovies(){
        //Room is empty fetch from network and save to the room
        return movieDao.getMovies()
                .filter(movieList ->!movieList.isEmpty())
                .switchIfEmpty(movieApiService.getApiMovies()
                        .doOnSuccess(data->{
            for ( Movie m : data)
                movieDao.insertMovie(m);
            }
        ));
    }

}
