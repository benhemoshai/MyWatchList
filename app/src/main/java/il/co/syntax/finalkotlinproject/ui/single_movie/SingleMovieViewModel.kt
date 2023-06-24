package il.co.syntax.finalkotlinproject.ui.single_movie

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import il.co.syntax.finalkotlinproject.data.models.Item
import il.co.syntax.finalkotlinproject.data.models.Movie
import il.co.syntax.finalkotlinproject.data.repository.MovieRepository
import il.co.syntax.finalkotlinproject.utils.Resource
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class SingleMovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {

    private val _id =  MutableLiveData<String>()

    private val _movie = _id.switchMap {
        movieRepository.getMovie(it)
    }

    val movie : LiveData<Resource<Movie>> = _movie

    fun setId(id : String) {
        _id.value = id
    }






}