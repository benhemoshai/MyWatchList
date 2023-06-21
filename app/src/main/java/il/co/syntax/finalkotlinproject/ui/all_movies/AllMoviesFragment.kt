package il.co.syntax.finalkotlinproject.ui.all_movies


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import il.co.syntax.finalkotlinproject.R
import il.co.syntax.finalkotlinproject.databinding.MoviesFragmentBinding
import il.co.syntax.finalkotlinproject.utils.Loading
import il.co.syntax.finalkotlinproject.utils.Success
import il.co.syntax.finalkotlinproject.utils.Error
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared

@AndroidEntryPoint
class AllMoviesFragment : Fragment(), MoviesAdapter.MovieItemListener {

    private val viewModel : AllMoviesViewModel by viewModels()

    private var binding : MoviesFragmentBinding by autoCleared()

    private  lateinit var  adapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MoviesFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MoviesAdapter(this)
        binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersRv.adapter = adapter

        viewModel.movies.observe(viewLifecycleOwner) {
            when(it.status) {
                is Loading -> binding.progressBar.visibility = View.VISIBLE

                is Success -> {
                    binding.progressBar.visibility = View.GONE
                    adapter.setMovies(it.status.data!!)
                }

                is Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(),it.status.message,Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onMovieClick(movieId: String) {
       findNavController().navigate(R.id.action_allMoviesFragment_to_singleMovieFragment,
           bundleOf("id" to movieId))
    }
}