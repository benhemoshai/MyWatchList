package il.co.syntax.finalkotlinproject.data.loca_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import il.co.syntax.finalkotlinproject.data.models.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao() : MovieDao

    companion object {

        @Volatile
        private var instance : AppDatabase?  = null

        fun getDatabase(context: Context) : AppDatabase =
            instance ?: synchronized(this) {
                Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,"movies")
                    .fallbackToDestructiveMigration().build().also {
                        instance = it
                    }
            }
    }
}