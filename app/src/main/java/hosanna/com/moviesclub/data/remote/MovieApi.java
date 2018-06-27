package hosanna.com.moviesclub.data.remote;

import hosanna.com.moviesclub.data.models.MovieResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("movie/popular")
    Single<MovieResponse> movieList(@Query("api_key") String key);
}
