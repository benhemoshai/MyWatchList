package il.co.syntax.finalkotlinproject.ui.itemupdate

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import il.co.syntax.finalkotlinproject.R
import il.co.syntax.finalkotlinproject.data.models.Item
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared
import il.co.syntax.finalkotlinproject.ui.ItemViewModel
import il.co.syntax.finalkotlinproject.databinding.DetailItemLayoutBinding
import il.co.syntax.finalkotlinproject.databinding.ItemUpdateLayoutBinding
import il.co.syntax.finalkotlinproject.ui.additem.AddItemViewModel
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class ItemUpdateFragment : Fragment() {

    private  var binding: ItemUpdateLayoutBinding by autoCleared()
    private val viewModel : ItemViewModel by activityViewModels()
    private var selectedDate: Date? = null
    private val updateItemViewModel: ItemUpdateViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemUpdateLayoutBinding.inflate(layoutInflater,container,false)


        viewModel.chosenItem.observe(viewLifecycleOwner) {
            binding.itemTitle.text = it.title
            binding.itemUpdatedDesc.setText(it.description)
            binding.itemUpdatedDate.text = it.date
            Glide.with(requireContext()).load(it.photo)
                .into(binding.itemImage)
        }
        binding.itemSaveChanges.setOnClickListener {
            viewModel.updateItem(binding.itemUpdatedDate.text.toString(),binding.itemUpdatedDesc.text.toString())
            findNavController().navigate(R.id.action_itemUpdateFragment_to_allItemsFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.itemUpdatedDate.setOnClickListener {
            showDatePicker()
        }

        updateItemViewModel.date.observe(viewLifecycleOwner){
            binding.itemUpdatedDate.text = it
        }


        super.onViewCreated(view, savedInstanceState)
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                val selectedCalendar = Calendar.getInstance()
                selectedCalendar.set(year, month, day)
                selectedDate = selectedCalendar.time

                updateSelectedDateView()
            },
            currentYear,
            currentMonth,
            currentDay
        )

        // Disable past dates
        datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
        datePickerDialog.show()
    }

    private fun updateSelectedDateView() {
        if (selectedDate != null) {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val formattedDate = dateFormat.format(selectedDate!!)
            updateItemViewModel.setDate(formattedDate)

        }
    }



}