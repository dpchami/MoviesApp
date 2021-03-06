package hosanna.com.moviesclub.data.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import hosanna.com.moviesclub.data.models.Movie;
import io.reactivex.Single;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(Movie movie);

    @Query("SELECT * FROM movies")
    Single<List<Movie>> getMovies();

    @Query("SELECT * FROM movies where id = :movieId")
    Single<Movie> getMovieById(int movieId);

    @Query("DELETE FROM movies where id = :movieId")
    void deleteMovie(int movieId);
}
