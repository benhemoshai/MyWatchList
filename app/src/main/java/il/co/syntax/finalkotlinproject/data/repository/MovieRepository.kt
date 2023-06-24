package il.co.syntax.finalkotlinproject.data.repository


import il.co.syntax.finalkotlinproject.data.remote_db.MovieRemoteDataSource
import il.co.syntax.finalkotlinproject.utils.performFetching
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource : MovieRemoteDataSource,
    //private val localDataSource : MovieDao
){

    fun getMovies() = performFetching(
      //  {localDataSource.getAllMovies()},
        {remoteDataSource.getMovies()}
       // {localDataSource.insertMovies(it.Search)}
    )

    fun getMovie(imdbID:String) = performFetching(
       // {localDataSource.getMovie(imdbID)},
        {remoteDataSource.getMovie(imdbID)}
        //{localDataSource.insertMovie(it)}
    )
}