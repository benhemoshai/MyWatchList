package il.co.syntax.finalkotlinproject.ui.all_movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import il.co.syntax.finalkotlinproject.data.models.Movie
import il.co.syntax.finalkotlinproject.databinding.ItemMovieBinding


class MoviesAdapter(private val listener : MovieItemListener) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private val movies = ArrayList<Movie>()

    class MovieViewHolder(private val itemBinding: ItemMovieBinding,
                          private val listener: MovieItemListener)
        : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {

        private lateinit var movie: Movie

        init {
            itemBinding.root.setOnClickListener(this)
        }

        fun bind(item: Movie) {
            this.movie = item
            itemBinding.title.text = item.Title
            itemBinding.yearAndType.text = "${item.Year} - ${item.Type}"
            Glide.with(itemBinding.root)
                .load(item.Poster)
                .circleCrop()
                .into(itemBinding.image)
        }

        override fun onClick(v: View?) {
            listener.onMovieClick(movie.imdbID)
        }
    }

    fun setMovies(movies : Collection<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(binding,listener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(movies[position])


    override fun getItemCount() = movies.size

    interface MovieItemListener {
        fun onMovieClick(movieId : String)
    }
}


