package il.co.syntax.finalkotlinproject.data.loca_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import il.co.syntax.finalkotlinproject.data.models.Item

@Database(entities = arrayOf(Item::class), version = 1, exportSchema = false)
abstract class ItemsDatabase : RoomDatabase(){

    abstract fun itemDao() : ItemDao

    companion object {

        @Volatile
        private var instance: ItemsDatabase? = null

        fun getDatabase(context: Context) = instance ?: synchronized(ItemsDatabase::class.java) {
            Room.databaseBuilder(context.applicationContext,
                ItemsDatabase::class.java,"items_database")
                .allowMainThreadQueries().build().also { instance = it }
        }
    }
}
