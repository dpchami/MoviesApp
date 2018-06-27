package hosanna.com.moviesclub.data.remote;

import java.util.ArrayList;
import java.util.List;

import hosanna.com.moviesclub.data.models.Movie;
import hosanna.com.moviesclub.util.Constants;
import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieApiService {

    private MovieApi movieApi;

    public MovieApiService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        movieApi = retrofit.create(MovieApi.class);
    }

    public Single<List<Movie>> getApiMovies() {
        return movieApi.movieList(Constants.API_KEY)
                .map(movieResponse -> {
                    List<Movie> results = movieResponse.getMovies(); //movies from remote
                    List<Movie> movies = new ArrayList<>();

                    for (int i = 0; i < results.size(); i++) {
                        Movie result = results.get(i);
                        Movie movie = new Movie();

                        movie.setAdult(result.getAdult());
                        movie.setBackdropPath(result.getBackdropPath());
                        movie.setGenreIds(result.getGenreIds());
                        movie.setId(result.getId());
                        movie.setOriginalLanguage(result.getOriginalLanguage());
                        movie.setOriginalTitle(result.getOriginalTitle());
                        movie.setOverview(result.getOverview());
                        movie.setPopularity(result.getPopularity());
                        movie.setPosterPath(result.getPosterPath());
                        movie.setReleaseDate(result.getReleaseDate());
                        movie.setTitle(result.getTitle());
                        movie.setVideo(result.getVideo());
                        movie.setVoteAverage(result.getVoteAverage());
                        movie.setVoteCount(result.getVoteCount());

                        movies.add(movie);
                    }

                    return movies;
                });
    }
}
