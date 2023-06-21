package il.co.syntax.finalkotlinproject.data.remote_db

import il.co.syntax.finalkotlinproject.data.models.Movie
import il.co.syntax.finalkotlinproject.data.models.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieInterface {

    @GET("/")
    suspend fun getAllMovies(
        @Query("s") s: String,
        @Query("page") page: Int,
        @Query("apikey") apikey: String
    ): Response<MovieResponse>

    @GET("/")
    suspend fun getMovie(
        @Query("i") i: String,
        @Query("apikey") apikey: String
    ): Response<Movie>
}
