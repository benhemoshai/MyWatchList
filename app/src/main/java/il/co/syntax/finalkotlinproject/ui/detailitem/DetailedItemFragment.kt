package il.co.syntax.finalkotlinproject.ui.detailitem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared
import il.co.syntax.finalkotlinproject.ui.ItemViewModel
import il.co.syntax.finalkotlinproject.databinding.DetailItemLayoutBinding

@AndroidEntryPoint
class DetailedItemFragment : Fragment() {

    private  var binding: DetailItemLayoutBinding by autoCleared()
    private val viewModel : ItemViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailItemLayoutBinding.inflate(layoutInflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.chosenItem.observe(viewLifecycleOwner) {
            binding.itemTitle.text = it.title
            binding.itemDesc.text = it.description
            binding.itemDate.text = it.date
            Glide.with(requireContext()).load(it.photo)
                .into(binding.itemImage)
        }
        super.onViewCreated(view, savedInstanceState)
    }
}