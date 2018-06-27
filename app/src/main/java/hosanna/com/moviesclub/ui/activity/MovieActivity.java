package hosanna.com.moviesclub.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import hosanna.com.moviesclub.R;
import hosanna.com.moviesclub.data.models.Movie;
import hosanna.com.moviesclub.ui.adapters.MovieAdapter;
import hosanna.com.moviesclub.ui.viewModels.MovieViewModel;

public class MovieActivity extends AppCompatActivity {

    public RecyclerView mRecyclerView;
    private MovieAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_activity);

        initUI();

        final MovieViewModel viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        viewModel.getLiveData().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                mAdapter.setMovieList(movies);
            }
        });

        viewModel.loadMovies();
    }

    private void initUI() {
        mAdapter = new MovieAdapter();
        mRecyclerView = findViewById(R.id.movie_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }
}
