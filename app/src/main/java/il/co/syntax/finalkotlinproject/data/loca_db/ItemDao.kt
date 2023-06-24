package il.co.syntax.finalkotlinproject.data.loca_db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import il.co.syntax.finalkotlinproject.data.models.Item


@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItem(item: Item)

    @Delete
    fun deleteItem(vararg item: Item)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateItem(item: Item)

    @Query("SELECT * from items_table ORDER BY date ASC")
    fun getItems() : LiveData<List<Item>>

    @Query("SELECT * from items_table WHERE title LIKE :title")
    fun getItem(title:String) : Item

    @Query("DELETE from items_table")
    fun deleteAll()
}
