package il.co.syntax.finalkotlinproject.ui.additem

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(): ViewModel() {
    private val _movieName = MutableLiveData<String>()
    val movieName: LiveData<String> = _movieName

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

  private val _image = MutableLiveData<String>()
    val image: LiveData<String> get()= _image


    fun setMovieName(name: String) {
        _movieName.value = name
    }

    fun setDate(date : String){
        _date.value = date
    }

    fun setImage(image : String){
        _image.value = image
    }


}