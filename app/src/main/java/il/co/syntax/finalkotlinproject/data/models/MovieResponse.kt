package il.co.syntax.finalkotlinproject.data.models

data class MovieResponse (
        val Response:String,
        val Search: List<Movie>,
        val totalResults:String
        )
