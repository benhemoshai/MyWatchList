package il.co.syntax.finalkotlinproject.data.repository


import il.co.syntax.finalkotlinproject.data.loca_db.MovieDao
import il.co.syntax.finalkotlinproject.data.remote_db.MovieRemoteDataSource
import il.co.syntax.finalkotlinproject.utils.performFetchingAndSaving
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource : MovieRemoteDataSource,
    private val localDataSource : MovieDao
){

    fun getMovies() = performFetchingAndSaving(
        {localDataSource.getAllMovies()},
        {remoteDataSource.getMovies()},
        {localDataSource.insertMovies(it.Search)}
    )

    fun getMovie(imdbID:String) = performFetchingAndSaving(
        {localDataSource.getMovie(imdbID)},
        {remoteDataSource.getMovie(imdbID)},
        {localDataSource.insertMovie(it)}
    )
}