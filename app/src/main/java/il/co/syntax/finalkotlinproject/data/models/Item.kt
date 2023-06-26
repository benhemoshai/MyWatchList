package il.co.syntax.finalkotlinproject.data.models

import android.media.Image
import android.view.View
import android.widget.ImageView
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "items_table")
data class Item(

    @ColumnInfo(name = "title")
    val title:String,

    @ColumnInfo(name = "description")
    var description:String,

    @ColumnInfo(name = "date")
    var date:String,

    @ColumnInfo(name = "image")
    val photo: String?)
{
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}


