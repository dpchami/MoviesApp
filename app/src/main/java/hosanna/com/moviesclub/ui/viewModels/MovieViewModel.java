package hosanna.com.moviesclub.ui.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import hosanna.com.moviesclub.data.MovieRepository;
import hosanna.com.moviesclub.data.local.MovieDao;
import hosanna.com.moviesclub.data.local.MovieDb;
import hosanna.com.moviesclub.data.models.Movie;
import hosanna.com.moviesclub.data.remote.MovieApiService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MovieViewModel extends AndroidViewModel{

    MovieRepository mMovieRepository;
    MutableLiveData<List<Movie>> mLiveData = new MutableLiveData<>();

    public MovieViewModel(@NonNull Application application) {
        super(application);

        MovieDao dao = MovieDb.getsInstance(application.getApplicationContext()).movieDao();
        MovieApiService api = new MovieApiService();

        mMovieRepository = new MovieRepository(dao,api);
    }

    public MutableLiveData<List<Movie>> getLiveData() {
        return mLiveData;
    }

    public void loadMovies(){
        mMovieRepository.getMovies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableSingleObserver<List<Movie>>() {
                    @Override
                    public void onSuccess(List<Movie> movies) {
                        mLiveData.postValue(movies);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("APP_ERROR", e.getMessage());
                    }
                });
    }
}
