package il.co.syntax.finalkotlinproject.data.loca_db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import il.co.syntax.finalkotlinproject.data.models.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies ORDER BY Year ASC")
    fun getAllMovies() : LiveData<List<Movie>>

    @Query("SELECT * FROM movies WHERE imdbID = :imdbID")
    fun getMovie(imdbID:String) : LiveData<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies : List<Movie>)
}