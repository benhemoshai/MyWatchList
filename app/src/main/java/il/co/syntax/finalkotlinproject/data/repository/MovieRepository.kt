package il.co.syntax.finalkotlinproject.data.repository


import il.co.syntax.finalkotlinproject.data.models.Movie
import il.co.syntax.finalkotlinproject.data.remote_db.MovieRemoteDataSource
import il.co.syntax.finalkotlinproject.utils.performFetching
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource : MovieRemoteDataSource,
){

    fun getMovies(query:String) = performFetching(
        {remoteDataSource.getMovies(query)}
    )

    fun getMovie(imdbID:String) = performFetching(
        {remoteDataSource.getMovie(imdbID)}
    )

}