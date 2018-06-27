package hosanna.com.moviesclub.data.local;

import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import hosanna.com.moviesclub.data.models.Movie;

@Database(entities = {Movie.class}, version = 1)
public abstract class MovieDb extends RoomDatabase {

    private static MovieDb sInstance;

    public static final String DATABASE_NAME = "moviedb";

    public abstract MovieDao movieDao();

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public static MovieDb getsInstance(Context context) {
        if (sInstance == null){
            sInstance = Room.databaseBuilder(context.getApplicationContext(), MovieDb.class, DATABASE_NAME)
                        .build();
        }
        return sInstance;
    }
}
