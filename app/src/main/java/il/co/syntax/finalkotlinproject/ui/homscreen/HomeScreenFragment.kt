package il.co.syntax.finalkotlinproject.ui.homscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import il.co.syntax.finalkotlinproject.R
import il.co.syntax.finalkotlinproject.databinding.HomeScreenLayoutBinding
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared


@AndroidEntryPoint
class HomeScreenFragment : Fragment() {

    private var binding: HomeScreenLayoutBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeScreenLayoutBinding.inflate(layoutInflater, container, false)

        Glide.with(requireContext()).load(R.drawable.popcorn).into(binding.image)

        binding.myWatchlistButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreenFragment_to_allItemsFragment)
        }

        binding.moviesButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreenFragment_to_allMoviesFragment)

        }

        return binding.root
    }
}