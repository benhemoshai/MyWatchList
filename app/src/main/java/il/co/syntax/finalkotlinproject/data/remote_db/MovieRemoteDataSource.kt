package il.co.syntax.finalkotlinproject.data.remote_db

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRemoteDataSource @Inject constructor(
    private val movieInterface:MovieInterface) : BaseDataSource() {

    suspend fun getMovies() = getResult { movieInterface.getAllMovies(s="avengers",page = 1, apikey = "f84f1a") }
    suspend fun getMovie(i : String) = getResult { movieInterface.getMovie(i,apikey = "f84f1a" )}
}