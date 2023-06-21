package il.co.syntax.finalkotlinproject.ui.all_movies

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import il.co.syntax.finalkotlinproject.data.repository.MovieRepository
import javax.inject.Inject

@HiltViewModel
class AllMoviesViewModel @Inject constructor(
    movieRepository: MovieRepository
) : ViewModel() {

    val movies  = movieRepository.getMovies()


}