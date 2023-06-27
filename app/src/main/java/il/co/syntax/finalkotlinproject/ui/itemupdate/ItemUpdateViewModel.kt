package il.co.syntax.finalkotlinproject.ui.itemupdate


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
class ItemUpdateViewModel @Inject constructor(): ViewModel() {

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    private val _desc = MutableLiveData<String>()
    val desc: LiveData<String> = _desc
    fun setDate(date : String) {
        _date.value = date
    }
    fun setDesc(desc : String) {
        _desc.value = desc
    }


}
