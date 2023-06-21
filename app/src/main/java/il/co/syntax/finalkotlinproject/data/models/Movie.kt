package il.co.syntax.finalkotlinproject.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    val Poster:String,
    val Title:String,
    val Type: String,
    val Year: String,
    val imdbID:String
)
