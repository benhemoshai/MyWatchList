package il.co.syntax.finalkotlinproject.ui.allitems


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import il.co.syntax.finalkotlinproject.ui.ItemViewModel
import il.co.syntax.finalkotlinproject.R
import android.app.AlertDialog
import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import il.co.syntax.finalkotlinproject.databinding.AllItemsFragmentBinding

@AndroidEntryPoint
class AllItemsFragment : Fragment() {

    private var _binding:AllItemsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel : ItemViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = AllItemsFragmentBinding.inflate(inflater,container,false)

        arguments?.getString("title")?.let {
            Toast.makeText(requireActivity(),it,Toast.LENGTH_SHORT).show()
        }

        //when the user clicks on "add a movie" button - moves to the "add item fragment"
        binding.flotaingAction.setOnClickListener {
            findNavController().navigate(R.id.action_allItemsFragment_to_addItemFragment)
        }

        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.items?.observe(viewLifecycleOwner) {


            binding.recycler.adapter = ItemAdapter(it, object : ItemAdapter.ItemListener {
                override fun onItemClicked(index: Int) {
                    viewModel.setItem(it[index])
                   findNavController().navigate(R.id.action_allItemsFragment_to_detailedItemFragment)
                }

            })
        }
        binding.recycler.layoutManager = GridLayoutManager(requireContext(),1)


        ItemTouchHelper(object : ItemTouchHelper.Callback() {

            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            )= makeFlag(ItemTouchHelper.ACTION_STATE_SWIPE, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deleteItem((binding.recycler.adapter as ItemAdapter)
                    .itemAt(viewHolder.adapterPosition))
                binding.recycler.adapter!!.notifyItemRemoved(viewHolder.adapterPosition)
            }
        }).attachToRecyclerView(binding.recycler)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
