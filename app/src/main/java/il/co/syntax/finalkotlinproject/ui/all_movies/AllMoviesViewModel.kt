package il.co.syntax.finalkotlinproject.ui.all_movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import il.co.syntax.finalkotlinproject.data.models.Movie
import il.co.syntax.finalkotlinproject.data.models.MovieResponse
import il.co.syntax.finalkotlinproject.data.repository.MovieRepository
import il.co.syntax.finalkotlinproject.utils.Resource
import javax.inject.Inject

@HiltViewModel
class AllMoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _movies = MutableLiveData<Resource<MovieResponse>>()
    val movies: LiveData<Resource<MovieResponse>>
        get() = _movies


    fun getMovies(query: String) {
        _movies.value = Resource.loading(null)
        movieRepository.getMovies(query).observeForever { resource ->
            _movies.value = resource
        }
    }

}
