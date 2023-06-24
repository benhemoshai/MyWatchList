package il.co.syntax.finalkotlinproject.ui


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import il.co.syntax.finalkotlinproject.data.models.Item
import il.co.syntax.finalkotlinproject.data.repository.ItemRepository
import kotlinx.coroutines.launch

class ItemViewModel(application: Application)
    : AndroidViewModel(application) {


    private val repository = ItemRepository(application)

    val items : LiveData<List<Item>>? = repository.getItems()

    private val _chosenItem = MutableLiveData<Item>()
    val chosenItem : LiveData<Item> get() = _chosenItem

    fun setItem(item: Item) {
        _chosenItem.value = item
    }

    fun addItem(item: Item) {
        viewModelScope.launch {
            repository.addItem(item)
        }

    }

    fun updateItem(item:Item){
        viewModelScope.launch {
            repository.updateItem(item)
        }
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            repository.deleteItem(item)
        }

    }


    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }

    }

}
