package il.co.syntax.finalkotlinproject.ui.single_movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import il.co.syntax.finalkotlinproject.R
import il.co.syntax.finalkotlinproject.data.models.Movie
import il.co.syntax.finalkotlinproject.databinding.MovieDetailFragmentBinding
import il.co.syntax.finalkotlinproject.ui.additem.AddItemViewModel
import il.co.syntax.finalkotlinproject.utils.Loading
import il.co.syntax.finalkotlinproject.utils.Success
import il.co.syntax.finalkotlinproject.utils.Error
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared

@AndroidEntryPoint
class SingleMovieFragment : Fragment() {

    private val viewModel : SingleMovieViewModel by viewModels()
    private val sharedviewModel : AddItemViewModel by activityViewModels()
    private var binding : MovieDetailFragmentBinding by autoCleared()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieDetailFragmentBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.movie.observe(viewLifecycleOwner) {
            when(it.status) {
                is Success -> {
                    binding.progressBar.visibility = View.GONE
                    updateMovie(it.status.data!!)
                    binding.characterCl.visibility = View.VISIBLE
                }
                is Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.characterCl.visibility = View.GONE
                }
                is Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(),it.status.message,Toast.LENGTH_LONG).show()
                }
            }
        }

        arguments?.getString("id")?.let {
            viewModel.setId(it)
        }


        binding.addButton.setOnClickListener {
            // Get the movie title from the binding
            val movieTitle = binding.title.text.toString()
            val image = binding.image
            // Set the movie title in the ViewModel
            sharedviewModel.setMovieName(movieTitle)
            findNavController().navigate(R.id.action_singleMovieFragment_to_addItemFragment)
        }
    }

    private fun updateMovie(movie: Movie) {
        binding.title.text = movie.Title
        binding.year.text = movie.Year
        binding.type.text = movie.Type
        binding.imdbID.text = movie.imdbID
        sharedviewModel.setImage(movie.Poster)
        Glide.with(requireContext()).load(movie.Poster).into(binding.image)
    }
}
